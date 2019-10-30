package co.aarav.mvc.model;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
@Entity(name = "employee_role_privileges")
public class EmployeeRolePrivileges {

	@XmlElement
	@Id
	@GeneratedValue
	private Long id;

	@XmlElement
	@Column(name = "role_id")
	private Long roleId;

	@XmlElement
	@ManyToOne
	@JoinColumn(name = "menu_id")
	private Menu menu;

	@XmlElement
	private Boolean hidden;

	@Transient
	private String[] multipleRole;

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Boolean getHidden() {
		return hidden;
	}

	public void setHidden(Boolean hidden) {
		this.hidden = hidden;
	}

	public String[] getMultipleRole() {
		return multipleRole;
	}

	public void setMultipleRole(String[] multipleRole) {
		this.multipleRole = multipleRole;
	}

	@Override
	public String toString() {
		return "EmployeeRolePrivileges [id=" + id + ", roleId=" + roleId + ", menu=" + menu + ", hidden=" + hidden
				+ ", multipleRole=" + Arrays.toString(multipleRole) + "]";
	}

}
