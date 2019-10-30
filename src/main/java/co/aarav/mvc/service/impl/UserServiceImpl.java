/**
*
* @author  : Durgesh Mudras
* @Date    : 22-11-2018
* @version : 1.0.0
* 
*/
package co.aarav.mvc.service.impl;

import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.aarav.mvc.dao.UserDao;
import co.aarav.mvc.model.Employee;
import co.aarav.mvc.model.EmployeeRolePrivileges;
import co.aarav.mvc.model.Menu;
import co.aarav.mvc.model.MenuHeader;
import co.aarav.mvc.service.UserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDao userDAO;

	@Autowired
	MessageSource messageSource;

	@Override
	@Transactional
	public List<Employee> findAllEmployee() {
		// TODO Auto-generated method stub
		return userDAO.findAllEmployee();
	}

	@Override
	@Transactional
	public Employee findByEmail(String emailAddress) {
		// TODO Auto-generated method stub
		return userDAO.findByEmail(emailAddress);
	}

	@Override
	@Transactional
	public Employee findByUserName(String useName) {
		// TODO Auto-generated method stub
		return userDAO.findByUserName(useName);
	}

	@Override
	@Transactional
	public List<EmployeeRolePrivileges> getEmployeePrivilegesByRole(Long roleId,Boolean hidden) {
		// TODO Auto-generated method stub
		return userDAO.getEmployeePrivilegesByRole(roleId,hidden);
	}

	@Override
	@Transactional
	public List<MenuHeader> getAllMenuHeader() {
		// TODO Auto-generated method stub
		return userDAO.getAllMenuHeader();
	}

	@Override
	@Transactional
	public void saveEmployeePrivileges(Long roleId) {
		// TODO Auto-generated method stub
		List<Menu> menuList = userDAO.getAllMenus();
		// List<EmployeeRolePrivileges> privilegesList = new
		// ArrayList<EmployeeRolePrivileges>();
		for (@SuppressWarnings("rawtypes")
		Iterator iterator = menuList.iterator(); iterator.hasNext();) {
			Menu menu = (Menu) iterator.next();
			EmployeeRolePrivileges rolePrivileges = new EmployeeRolePrivileges();
			rolePrivileges.setHidden(true);
			rolePrivileges.setMenu(menu);
			rolePrivileges.setRoleId(roleId);

			userDAO.saveEmployeePrivileges(rolePrivileges);

//			privilegesList.add(rolePrivileges) ;
		}

	}

	@Override
	@Transactional
	public List<Menu> getAllMenus() {
		// TODO Auto-generated method stub
		return userDAO.getAllMenus();
	}

	@Override
	@Transactional
	public void updateEmployeePrivileges(EmployeeRolePrivileges employeeRolePrivilegesObj) {
		// TODO Auto-generated method stub
		userDAO.updateEmployeePrivileges(employeeRolePrivilegesObj);
	}

}
