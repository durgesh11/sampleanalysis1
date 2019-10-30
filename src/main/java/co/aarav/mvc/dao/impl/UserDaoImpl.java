/**
*
* @author  : Durgesh Mudras
* @Date    : 19-11-2018
* @version : 1.0.0
* 
*/
package co.aarav.mvc.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.aarav.mvc.dao.UserDao;
import co.aarav.mvc.model.Employee;
import co.aarav.mvc.model.EmployeeRolePrivileges;
import co.aarav.mvc.model.Menu;
import co.aarav.mvc.model.MenuHeader;
import co.aarav.mvc.model.RoleMaster;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;
	private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	@Override
	public Employee findByEmail(String emailAddress) {
		// TODO Auto-generated method stub

		Criteria criteria = sessionFactory.openSession().createCriteria(Employee.class); // .addOrder(Order.asc("user_name"));
		criteria.add(Restrictions.eq("emailAddress", emailAddress));

		Employee employee = (Employee) criteria.uniqueResult();
		if (employee != null) {
			// Hibernate.initialize(employee.getUserGroup());
		}
		return employee;
	}

	@Override
	public List<Employee> findAllEmployee() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee findByUserName(String userName) {
		// TODO Auto-generated method stub
		Criteria criteria = sessionFactory.openSession().createCriteria(Employee.class); // .addOrder(Order.asc("user_name"));
		criteria.add(Restrictions.eq("userName", userName));

		Employee employee = (Employee) criteria.uniqueResult();
		if (employee != null) {
			// Hibernate.initialize(employee.getUserGroup());
		}
		return employee;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeRolePrivileges> getEmployeePrivilegesByRole(Long roleId,Boolean hidden) {
		List<EmployeeRolePrivileges> list = null;
		try {
			logger.info("roleId :" + roleId);
			Criteria criteria = sessionFactory.openSession().createCriteria(EmployeeRolePrivileges.class); // .addOrder(Order.asc("user_name"));
			criteria.add(Restrictions.eq("roleId", roleId));
			
			if (hidden != null) {
				if (hidden) {
					criteria.add(Restrictions.eq("hidden", true));
				} else {
					criteria.add(Restrictions.eq("hidden", false));
				}
			}
			//
			list = criteria.list();

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception :", e);
		}
		// TODO Auto-generated method stub
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MenuHeader> getAllMenuHeader() {
		// TODO Auto-generated method stub
		List<MenuHeader> list = null;
		try {

			Criteria criteria = sessionFactory.openSession().createCriteria(MenuHeader.class); // .addOrder(Order.asc("user_name"));
			criteria.add(Restrictions.eq("hidden", false));
			list = criteria.list();

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception :", e);
		}
		// TODO Auto-generated method stub
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Menu> getAllMenus() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		List<Menu> list = null;
		try {

			Criteria criteria = sessionFactory.openSession().createCriteria(Menu.class); // .addOrder(Order.asc("user_name"));
			criteria.add(Restrictions.eq("hidden", false));
			list = criteria.list();

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception :", e);
		}
		// TODO Auto-generated method stub
		return list;
	}

	@Override
	public void saveEmployeePrivileges(EmployeeRolePrivileges privilegesList) {
		// TODO Auto-generated method stub
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();

			session.save(privilegesList);

			session.getTransaction().commit();
			session.close();
			

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception :", e);
		}

	}

	@Override
	public void updateEmployeePrivileges(EmployeeRolePrivileges employeeRolePrivilegesObj) {
		// TODO Auto-generated method stub
		try {
			EmployeeRolePrivileges employeeRolePrivileges = (EmployeeRolePrivileges) sessionFactory.getCurrentSession().load(EmployeeRolePrivileges.class, employeeRolePrivilegesObj.getId());
			employeeRolePrivileges.setHidden(employeeRolePrivilegesObj.getHidden());
			//employeeRolePrivileges.setMenu(employeeRolePrivilegesObj.getMenu());
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
