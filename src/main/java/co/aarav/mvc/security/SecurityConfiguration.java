/**
*
* @author  : Durgesh Mudras
* @Date    : 16-11-2018
* @version : 1.0.0
* 
*/
package co.aarav.mvc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * This Class gives all Security Configuration Details info.
 */
@Configuration
@EnableWebSecurity
@ComponentScan({ "co.aarav.mvc.controller", "co.aarav.mvc.dao.impl", "co.aarav.mvc.service.impl" })
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("customUserDetailsService")
	UserDetailsService userDetailsService;

	@Autowired
	PersistentTokenRepository tokenRepository;

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
				.antMatchers("/", "/list", "/master-items-groups.html/**", "/setting.html/**", "/singleUpload/**",
						"/saveSetting/**")
				.access("hasRole('USER') or hasRole('ADMIN') or hasRole('DBA')")
				.antMatchers("/newuser/**", "/delete-user-*", "/mvc/**")
				.access("hasRole('ADMIN') or hasRole('ADMIN') or hasRole('DBA') ")

				.and().formLogin().loginPage("/login").loginProcessingUrl("/login").usernameParameter("emailAddress")
				.passwordParameter("password")

				.and().rememberMe().rememberMeParameter("remember-me").tokenRepository(tokenRepository)
				.tokenValiditySeconds(60)

				.and().csrf().and().exceptionHandling().accessDeniedPage("/Access_Denied")
//				
				.and().logout().clearAuthentication(true).logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login?logout").deleteCookies("JSESSIONID").invalidateHttpSession(true).permitAll();

	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {

		@SuppressWarnings("deprecation")
		org.springframework.security.authentication.encoding.PasswordEncoder passwordEncoder = new Md5PasswordEncoder();
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder);
		return authenticationProvider;
	}

	@Bean
	public PersistentTokenBasedRememberMeServices getPersistentTokenBasedRememberMeServices() {
		PersistentTokenBasedRememberMeServices tokenBasedservice = new PersistentTokenBasedRememberMeServices(
				"remember-me", userDetailsService, tokenRepository);
		return tokenBasedservice;
	}

	@Bean
	public AuthenticationTrustResolver getAuthenticationTrustResolver() {
		return new AuthenticationTrustResolverImpl();
	}
}
