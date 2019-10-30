/**
*
* @author  : Durgesh Mudras
* @Date    : 17-10-2018
* @version : 1.0.0
* 
*/
package co.aarav.mvc.service.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.aarav.mvc.dao.MasterDao;
import co.aarav.mvc.model.Country;
import co.aarav.mvc.model.Employee;
import co.aarav.mvc.model.RoleMaster;
import co.aarav.mvc.service.MasterService;

@Service("masterService")
@Transactional
public class MasterServiceImpl implements MasterService {

	private static Logger logger = Logger.getLogger(MasterServiceImpl.class);

	@Autowired
	private MasterDao masterDAO;

	@Override
	@Transactional
	public List<RoleMaster> getRoleMastersList() {
		// TODO Auto-generated method stub
		return masterDAO.getRoleMastersList();
	}

	@Override
	@Transactional
	public Boolean isRoleMasterAlreadyExist(String name) {
		// TODO Auto-generated method stub
		return masterDAO.isRoleMasterAlreadyExist(name);
	}

	@Override
	@Transactional
	public Long saveRoleMaster(RoleMaster roleMaster) {
		// TODO Auto-generated method stub
		return masterDAO.saveRoleMaster(roleMaster);
	}

	@Override
	@Transactional
	public void deleteRoleMaster(Long roleId) {
		// TODO Auto-generated method stub
		masterDAO.deleteRoleMaster(roleId);
	}

	@Override
	@Transactional
	public RoleMaster getRoleMaster(Long id) {
		// TODO Auto-generated method stub
		return masterDAO.getRoleMaster(id);
	}

	@Override
	@Transactional
	public void updateRoleMaster(RoleMaster roleMaster) {
		// TODO Auto-generated method stub
		masterDAO.updateRoleMaster(roleMaster);
	}

	@Override
	@Transactional
	public Boolean isEmployeeAlreadyExist(String name) {
		// TODO Auto-generated method stub
		return masterDAO.isEmployeeAlreadyExist(name);
	}

	@Override
	@Transactional
	public Long saveEmployee(Employee employee) {
		// TODO Auto-generated method stub
		try {
			System.out.println("employee :" + employee.toString());

			employee.setRegistrationDate(new Date());
			Country country = new Country();
			country.setId(105L);
			employee.setCountry(country);
			String md5Password = getMD5(employee.getPassword().trim());
			employee.setPassword(null);
			employee.setPassword(md5Password);

			logger.info("Employee :" + employee.toString());

			return masterDAO.saveEmployee(employee);

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0L;

	}

	@Override
	@Transactional
	public List<Employee> getEmployeeList() {
		// TODO Auto-generated method stub
		return masterDAO.getEmployeeList();
	}

	@Override
	@Transactional
	public Boolean isEmployeeEmailAlreadyExist(String emailAddress) {
		// TODO Auto-generated method stub
		return masterDAO.isEmployeeEmailAlreadyExist(emailAddress);
	}

	public static String getMD5(String data) throws NoSuchAlgorithmException {
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");

		messageDigest.update(data.getBytes());
		byte[] digest = messageDigest.digest();
		StringBuffer sb = new StringBuffer();
		for (byte b : digest) {
			sb.append(Integer.toHexString((int) (b & 0xff)));
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		String md5;
		try {
			md5 = getMD5("1234");
			System.out.println("MD5 :" + md5);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	@Transactional
	public void deleteEmployee(Long employeeId) {
		// TODO Auto-generated method stub
		masterDAO.deleteEmployee(employeeId);
	}

	@Override
	@Transactional
	public Employee getEmployee(Long id) {
		// TODO Auto-generated method stub
		return masterDAO.getEmployee(id);
	}

	@Override
	@Transactional
	public void updateEmployee(Employee employee) {
		// TODO Auto-generated method stub

		String md5Password;
		try {
			md5Password = getMD5(employee.getPassword().trim());
			employee.setPassword(null);
			employee.setPassword(md5Password);

			masterDAO.updateEmployee(employee);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	@Transactional
	public void updateEmployeeLastLogin(Employee employee) {
		// TODO Auto-generated method stub
		masterDAO.updateEmployeeLastLogin(employee);
	}

}
