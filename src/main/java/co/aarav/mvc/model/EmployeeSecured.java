/**
*
* @author  : Durgesh Mudras
* @version : 1.0.0
* 
*/
package co.aarav.mvc.model;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import co.aarav.mvc.enums.EmployeeType;

public class EmployeeSecured extends User {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String userName;

	private String password;

	private String firstName;

	private String lastName;

	private String telephone;

	private String emailAddress;

	private Date registrationDate;

	private Calendar lastLogin;

	private EmployeeType employeeType;

	private Boolean deleted;

	private Boolean active;

	private String suspendedReason;

	private String loginRole;

	private RoleMaster role;

	public EmployeeSecured(String userName, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities,
			Long id, String firstName, String lastName, String telephone, String emailAddress, Date registrationDate,
			Calendar lastLogin, EmployeeType employeeType, Boolean deleted, Boolean active, String suspendedReason,
			String loginRole, RoleMaster role) {
		super(userName, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		// TODO Auto-generated constructor stub

		this.userName = userName;
		this.password = password;

		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.telephone = telephone;
		this.emailAddress = emailAddress;
		this.registrationDate = registrationDate;

		this.lastLogin = lastLogin;
		this.employeeType = employeeType;
		this.deleted = deleted;
		this.active = active;
		this.suspendedReason = suspendedReason;
		this.loginRole = loginRole;

		this.role = role;
	}

	/**
	 * @return the loginRole
	 */
	public String getLoginRole() {
		return loginRole;
	}

	/**
	 * @param loginRole the loginRole to set
	 */
	public void setLoginRole(String loginRole) {
		this.loginRole = loginRole;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
 

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Calendar getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Calendar lastLogin) {
		this.lastLogin = lastLogin;
	}

	public EmployeeType getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(EmployeeType employeeType) {
		this.employeeType = employeeType;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getSuspendedReason() {
		return suspendedReason;
	}

	public void setSuspendedReason(String suspendedReason) {
		this.suspendedReason = suspendedReason;
	}

	public RoleMaster getRole() {
		return role;
	}

	public void setRole(RoleMaster role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "EmployeeSecured [id=" + id + ", userName=" + userName + ", password=" + password + ", firstName="
				+ firstName + ", lastName=" + lastName + ", telephone=" + telephone + ", emailAddress=" + emailAddress
				+ ", registrationDate=" + registrationDate + ", lastLogin=" + lastLogin + ", employeeType="
				+ employeeType + ", deleted=" + deleted + ", active=" + active + ", suspendedReason=" + suspendedReason
				+ ", loginRole=" + loginRole + ", role=" + role + "]";
	}
}
