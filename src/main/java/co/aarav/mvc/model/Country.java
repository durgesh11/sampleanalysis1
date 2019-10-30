package co.aarav.mvc.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Durgesh Mudras 
 *  This is the domain object used to hold the details
 *         for the countries
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
@Entity(name = "countries")
public class Country implements Comparable<Country> {

	@XmlElement
	@Id
	@GeneratedValue
	private Long id;

	@XmlElement
	@Column(name = "code")
	private String code;

	@XmlElement
	@Column(name = "name")
	private String name;

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * overriding equals method
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Country)) {
			return false;
		}
		Country country = (Country) obj;

		return id.equals(country.getId()) && code.equals(country.getCode()) && name.equals(country.getName());
	}

	/**
	 * overriding toString
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[Country: ");
		builder.append("id=").append(this.id).append(", ");
		builder.append("code=").append(this.code).append(", ");
		builder.append("name=").append(this.name);
		builder.append("]");
		return builder.toString();
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int compareTo(Country country) {
		if (country.getName() == null && this.getName() == null) {
			return 0;
		}
		if (this.getName() == null) {
			return 1;
		}
		if (country.getName() == null) {
			return -1;
		}
		return this.getName().trim().toLowerCase().compareTo(country.getName().trim().toLowerCase());
	}

}
