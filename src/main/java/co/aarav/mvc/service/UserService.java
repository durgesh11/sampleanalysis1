/**
*
* @author  : Durgesh Mudras
* @Date    : 22-11-2018
* @version : 1.0.0
* 
*/
package co.aarav.mvc.service;

import java.util.List;

import co.aarav.mvc.model.Employee;
import co.aarav.mvc.model.EmployeeRolePrivileges;
import co.aarav.mvc.model.Menu;
import co.aarav.mvc.model.MenuHeader;

public interface UserService {

	List<Employee> findAllEmployee();

	Employee findByEmail(String emailAddress);

	Employee findByUserName(String useName);
	
	List<EmployeeRolePrivileges> getEmployeePrivilegesByRole(Long roleId,Boolean hidden);

	List<MenuHeader> getAllMenuHeader();

	void saveEmployeePrivileges(Long id);
 
	List<Menu> getAllMenus();

	void updateEmployeePrivileges(EmployeeRolePrivileges employeeRolePrivilegesObj);

}