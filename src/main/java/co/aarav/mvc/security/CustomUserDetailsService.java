/**
*
* @author  : Durgesh Mudras
* @Date    : 16-11-2018
* @version : 1.0.0
* 
*/
package co.aarav.mvc.security;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.aarav.mvc.model.Employee;
import co.aarav.mvc.model.EmployeeSecured;
import co.aarav.mvc.service.UserService;

/**
 * This Class gives all user Details info.
 */
@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

	@Autowired
	private UserService userService;

	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String useName) throws UsernameNotFoundException {
		logger.info("INSIDE LoadUserByUserName..........." + useName);

		EmployeeSecured employeeSecured = null;
		Employee emp = userService.findByUserName(useName);
		
		

		if (emp == null) {
			logger.info("User not found ************");
			throw new UsernameNotFoundException("Username not found*");
		}
		
		logger.info("emp :"+emp.toString());

		employeeSecured = new EmployeeSecured(emp.getUserName(), emp.getPassword(), true, true, true, true,
				getGrantedAuthorities(emp), emp.getId(), emp.getFirstName(), emp.getLastName(), emp.getTelephone(),
				emp.getEmailAddress(), emp.getRegistrationDate(), emp.getLastLogin(), emp.getEmployeeType(),
				emp.getDeleted(), emp.getActive(), emp.getSuspendedReason(), emp.getLoginRole(),emp.getRole());

		return employeeSecured;
	}

	/**
	 * This method give Grant Authorities for particular User.
	 */
	private List<GrantedAuthority> getGrantedAuthorities(Employee emp) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		logger.info("authorities :" + authorities);
		return authorities;
	}

	/**
	 * This MAIN method to check Password encoder. Just run only this file will gwt
	 * O/P.
	 */
	public static void main(String[] args) {
		String encoded = new BCryptPasswordEncoder().encode("1234");
		System.err.println("Encoded :" + encoded);

		String encoded1 = NoOpPasswordEncoder.getInstance().encode("1234");
		System.err.println("Encoded1 :" + encoded1);

		String encoded2 = new StandardPasswordEncoder().encode("1234");
		System.err.println("Encoded2 :" + encoded2);
	}
}
