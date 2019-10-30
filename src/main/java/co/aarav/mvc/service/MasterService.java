/**
*
* @author  : Durgesh Mudras
* @Date    : 17-10-2018
* @version : 1.0.0
* 
*/
package co.aarav.mvc.service;

import java.util.List;

import co.aarav.mvc.model.Employee;
import co.aarav.mvc.model.RoleMaster;

public interface MasterService {

	List<RoleMaster> getRoleMastersList();

	Boolean isRoleMasterAlreadyExist(String name);

	Long saveRoleMaster(RoleMaster roleMaster);

	void deleteRoleMaster(Long roleId);

	RoleMaster getRoleMaster(Long id);

	void updateRoleMaster(RoleMaster roleMaster);
	
	Boolean isEmployeeAlreadyExist(String name);

	Long saveEmployee(Employee employee);

	List<Employee> getEmployeeList();

	Boolean isEmployeeEmailAlreadyExist(String emailAddress);

	void deleteEmployee(Long employeeId);
	
	Employee getEmployee(Long id);

	void updateEmployee(Employee employee);

	void updateEmployeeLastLogin(Employee employee2);


}