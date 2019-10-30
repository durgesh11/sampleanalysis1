package co.aarav.mvc.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import co.aarav.mvc.enums.EmployeeType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
@Entity
@Table(name = "employee")
public class Employee {

	@XmlElement
	@Id
	@GeneratedValue
	private Long id;

	@XmlElement
	@Column(length = 50, name = "username")
	private String userName;

	@XmlElement
	@Column(length = 50, name = "password")
	private String password;

	@XmlElement
	@Column(length = 50, name = "first_name")
	private String firstName;

	@XmlElement
	@Column(length = 50, name = "last_name")
	private String lastName;

	@XmlElement
	@Column(length = 50, name = "telephone")
	private String telephone;

	@XmlElement
	@Column(length = 50, name = "email_address")
	private String emailAddress;

	@XmlElement
	@Column(name = "registration_date")
	private Date registrationDate;

	@XmlElement
	@Column(name = "last_login")
	private Calendar lastLogin;

	@XmlElement
	@ManyToOne
	@JoinColumn(name = "role_id")
	private RoleMaster role;

	@Transient
	private String confirmPassword;

	@XmlElement
	@Column(name = "emp_type", columnDefinition = "integer", nullable = false)
	@Type(type = "co.aarav.mvc.hibernate.GenericEnumUserType", parameters = {
			@Parameter(name = "enumClass", value = "co.aarav.mvc.enums.EmployeeType"),
			@Parameter(name = "identifierMethod", value = "toInt"),
			@Parameter(name = "valueOfMethod", value = "fromInt") })
	private EmployeeType employeeType;

	@XmlElement
	private Boolean deleted = false;

	@XmlElement
	@Column(name = "active")
	private Boolean active = true;

	@XmlElement
	private String suspendedReason;

	@XmlElement
	@Column(name = "emp_role")
	private String loginRole;

	@XmlElement
	@OneToOne
	@JoinColumn(name = "country_id")
	private Country country;

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

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", userName=" + userName + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", telephone=" + telephone + ", emailAddress=" + emailAddress
				+ ", registrationDate=" + registrationDate + ", lastLogin=" + lastLogin + ", role=" + role
				+ ", confirmPassword=" + confirmPassword + ", employeeType=" + employeeType + ", deleted=" + deleted
				+ ", active=" + active + ", suspendedReason=" + suspendedReason + ", loginRole=" + loginRole
				+ ", country=" + country + "]";
	}
}
