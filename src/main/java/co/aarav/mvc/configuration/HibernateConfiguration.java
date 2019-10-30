/**
*
* @author  : Durgesh Mudras
* @Date    : 16-10-2019
* @version : 1.0.0
* 
*/
package co.aarav.mvc.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan({ "co.aarav.mvc.configuration" })
@PropertySource(value = { "classpath:application.properties" }) 
public class HibernateConfiguration {

	@Autowired
	private Environment environment;

	@Bean(name = "sessionFactory")
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSourceHq());
		sessionFactory.setPackagesToScan(new String[] { "co.aarav.mvc.model" });
		sessionFactory.setHibernateProperties(hibernateProperties());

		return sessionFactory;
	}

	@Bean(name = "sessionFactoryMaintenance")
	public LocalSessionFactoryBean sessionFactoryMaintenance() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSourceMaintananceDB());
		sessionFactory.setPackagesToScan(new String[] { "co.aarav.mvc.model" });
		sessionFactory.setHibernateProperties(hibernatePropertiesMaintenance());

		return sessionFactory;
	}

	/**
	 * Configuring dataSource for particular database.
	 */
	
	/*
	 * @Bean public DataSource dataSource() { final JndiDataSourceLookup dsLookup =
	 * new JndiDataSourceLookup(); dsLookup.setResourceRef(true); DataSource
	 * dataSource = dsLookup.getDataSource("jdbc/iqoshq"); return dataSource; }
	 * 
	 * @Bean public DataSource dataSourceMaintenance1() { final JndiDataSourceLookup
	 * dsLookup = new JndiDataSourceLookup(); dsLookup.setResourceRef(true);
	 * DataSource dataSource = dsLookup.getDataSource("jdbc/iqosMaintenance");
	 * return dataSource; }
	 */
	
	@Bean
	public DataSource dataSourceHq() {
 		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
		dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
		dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
		dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
		return dataSource;
	}

	@Bean
	public DataSource dataSourceMaintananceDB() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName.maintenance"));
		dataSource.setUrl(environment.getRequiredProperty("jdbc.url.maintenance"));
		dataSource.setUsername(environment.getRequiredProperty("jdbc.username.maintenance"));
		dataSource.setPassword(environment.getRequiredProperty("jdbc.password.maintenance"));
		return dataSource;
	}

	
 

	 

	/**
	 * Property file for sql.
	 */
	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
		properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
		properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
		return properties;
	}

	private Properties hibernatePropertiesMaintenance() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
		properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
		properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
		return properties;
	}

	/**
	 * Configure sessionFactory2 for HQ DB transaction.
	 */

	@Bean
	public SessionFactory db1SessionFactory() {
		return sessionFactory().getObject();
	}

	@Bean
	public SessionFactory db2SessionFactory() {
		return sessionFactoryMaintenance().getObject();
	}

	@Bean(name = "DB1TransactionManager")
	@Primary
	public HibernateTransactionManager db1TransactionManager() {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(this.db1SessionFactory());
		return txManager;
	}

}
