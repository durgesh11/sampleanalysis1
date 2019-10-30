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
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.aarav.mvc.dao.MasterDao;
import co.aarav.mvc.model.Employee;
import co.aarav.mvc.model.RoleMaster;

@Repository("masterDao")
public class MasterDaoImpl implements MasterDao {

	@Autowired
	private SessionFactory sessionFactory;
	private static final Logger logger = LoggerFactory.getLogger(MasterDaoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<RoleMaster> getRoleMastersList() {
		// TODO Auto-generated method stub
		List<RoleMaster> list = null;
		try {
			logger.info("roleId :");
			Criteria criteria = sessionFactory.openSession().createCriteria(RoleMaster.class); // .addOrder(Order.asc("user_name"));
			// criteria.add(Restrictions.eq("roleId", roleId));
			// criteria.add(Restrictions.eq("hidden", false));
			list = criteria.list();

		} catch (Exception e) {
			// TODO: handle exceptions
			logger.error("Exception :", e);
		}
		// TODO Auto-generated method stub
		return list;
	}

	@Override
	public Boolean isRoleMasterAlreadyExist(String name) {
		// TODO Auto-generated method stub
		Boolean isRoleMasterAlreadyExist = false;
		try {
			logger.info("roleId :");
			Criteria criteria = sessionFactory.openSession().createCriteria(RoleMaster.class); // .addOrder(Order.asc("user_name"));
			criteria.add(Restrictions.eq("name", name));
			criteria.setProjection(Projections.rowCount());
			long count = (Long) criteria.uniqueResult();
			// criteria.add(Restrictions.eq("hidden", false));
			logger.info("isRoleMasterExist :" + count);
			if (count > 0) {
				isRoleMasterAlreadyExist = true;
			}

		} catch (Exception e) {
			// TODO: handle exceptions
			logger.error("Exception :", e);
		}
		// TODO Auto-generated method stub
		return isRoleMasterAlreadyExist;
	}

	@Override
	public Long saveRoleMaster(RoleMaster roleMaster) {
		// TODO Auto-generated method stub
		try {
			logger.info("roleId :");
			Long roleId = (Long) sessionFactory.openSession().save(roleMaster); // .addOrder(Order.asc("user_name"));

			logger.info("roleId: " + roleId);
			return roleId;
		} catch (Exception e) {
			// TODO: handle exceptions
			logger.error("Exception :", e);
		}
		return 0L;
	}

	@Override
	public void deleteRoleMaster(Long roleId) {
		// TODO Auto-generated method stub
		try {
			logger.info("roleId :");
			RoleMaster roleMaster = (RoleMaster) sessionFactory.getCurrentSession().load(RoleMaster.class, roleId);
			sessionFactory.getCurrentSession().delete(roleMaster);
			logger.info("roleId: " + roleId);
		} catch (Exception e) {
			// TODO: handle exceptions
			logger.error("Exception :", e);
		}
	}

	@Override
	public RoleMaster getRoleMaster(Long id) {
		// TODO Auto-generated method stub
		try {
			logger.info("id :" + id);

			RoleMaster roleMaster = (RoleMaster) sessionFactory.getCurrentSession().get(RoleMaster.class, id);

			return roleMaster;
		} catch (Exception e) {
			// TODO: handle exceptions
			logger.error("Exception :", e);
		}
		return null;
	}

	@Override
	public void updateRoleMaster(RoleMaster roleMaster) {
		// TODO Auto-generated method stub
		try {
			logger.info("roleId :" + roleMaster.getId());
			RoleMaster roleMasterObj = (RoleMaster) sessionFactory.getCurrentSession().load(RoleMaster.class,
					roleMaster.getId());
			roleMasterObj.setName(roleMaster.getName());

		} catch (Exception e) {
			// TODO: handle exceptions
			logger.error("Exception :", e);
		}
	}

	@Override
	public Boolean isEmployeeAlreadyExist(String name) {
		// TODO Auto-generated method stub
		Boolean isEmployeeAlreadyExist = false;
		try {
			logger.info("roleId :");
			Criteria criteria = sessionFactory.openSession().createCriteria(Employee.class); // .addOrder(Order.asc("user_name"));
			criteria.add(Restrictions.eq("userName", name));
			criteria.setProjection(Projections.rowCount());
			long count = (Long) criteria.uniqueResult();
			// criteria.add(Restrictions.eq("hidden", false));
			logger.info("isEmployeeAlreadyExist :" + count);
			if (count > 0) {
				isEmployeeAlreadyExist = true;
			}

		} catch (Exception e) {
			// TODO: handle exceptions
			logger.error("Exception :", e);
		}
		// TODO Auto-generated method stub
		return isEmployeeAlreadyExist;
	}

	@Override
	public Long saveEmployee(Employee employee) {
		// TODO Auto-generated method stub
		try {
			logger.info("roleId :");
			Long employeeId = (Long) sessionFactory.openSession().save(employee); // .addOrder(Order.asc("user_name"));
			logger.info("EmployeeId:-- " + employeeId);
			return employeeId;
		} catch (Exception e) {
			// TODO: handle exceptions
			logger.error("Exception :", e);
		}
		return 0L;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getEmployeeList() {
		// TODO Auto-generated method stub
		List<Employee> list = null;
		try {
			Criteria criteria = sessionFactory.openSession().createCriteria(Employee.class); // .addOrder(Order.asc("user_name"));
			// criteria.add(Restrictions.eq("roleId", roleId));
			criteria.add(Restrictions.eq("deleted", false));
			list = criteria.list();

		} catch (Exception e) {
			// TODO: handle exceptions
			logger.error("Exception :", e);
		}
		// TODO Auto-generated method stub
		return list;
	}

	@Override
	public Boolean isEmployeeEmailAlreadyExist(String emailAddress) {
		// TODO Auto-generated method stub

		Boolean isEmployeeEmailAlreadyExist = false;
		try {
			logger.info("roleId :");
			Criteria criteria = sessionFactory.openSession().createCriteria(Employee.class); // .addOrder(Order.asc("user_name"));
			criteria.add(Restrictions.eq("emailAddress", emailAddress.trim()));
			criteria.setProjection(Projections.rowCount());
			long count = (Long) criteria.uniqueResult();
			logger.info("isEmployeeEmailAlreadyExist :" + count);
			if (count > 0) {
				isEmployeeEmailAlreadyExist = true;
			}

		} catch (Exception e) {
			// TODO: handle exceptions
			logger.error("Exception :", e);
		}
		// TODO Auto-generated method stub
		return isEmployeeEmailAlreadyExist;
	}

	@Override
	public void deleteEmployee(Long employeeId) {
		// TODO Auto-generated method stub
		try {
			logger.info("roleId :");
			Employee employee = (Employee) sessionFactory.getCurrentSession().load(Employee.class, employeeId);
			employee.setDeleted(true);
//			sessionFactory.getCurrentSession().delete(employee);
			logger.info("employeeId: " + employeeId);
		} catch (Exception e) {
			// TODO: handle exceptions
			logger.error("Exception :", e);
		}
	}

	@Override
	public Employee getEmployee(Long id) {
		// TODO Auto-generated method stub
		try {
			logger.info("id :" + id);

			Employee employee = (Employee) sessionFactory.getCurrentSession().get(Employee.class, id);

			return employee;
		} catch (Exception e) {
			// TODO: handle exceptions
			logger.error("Exception :", e);
		}
		return null;
	}

	@Override
	public void updateEmployee(Employee employee) {
		// TODO Auto-generated method stub
		try {
			logger.info("roleId :" + employee.getId());
			Employee employeeObj = (Employee) sessionFactory.getCurrentSession().load(Employee.class, employee.getId());
			employeeObj.setFirstName(employee.getFirstName());
			employeeObj.setLastName(employee.getLastName());
			employeeObj.setTelephone(employee.getTelephone());

			employeeObj.setPassword(employee.getPassword());

		} catch (Exception e) {
			// TODO: handle exceptions
			logger.error("Exception :", e);
		}
	}

	@Override
	public void updateEmployeeLastLogin(Employee employee) {
		// TODO Auto-generated method stub
		try {
			logger.info("roleId :" + employee.getId());
			Employee employeeObj = (Employee) sessionFactory.getCurrentSession().load(Employee.class, employee.getId());
			employeeObj.setLastLogin(employee.getLastLogin());

		} catch (Exception e) {
			// TODO: handle exceptions
			logger.error("Exception :", e);
		}
	}

}
