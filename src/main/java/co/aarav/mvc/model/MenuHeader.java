package co.aarav.mvc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
@Entity(name = "menu_header")
public class MenuHeader {

	@XmlElement
	@Id
	@GeneratedValue
	private Long id;

	@XmlElement
	@Column(length = 50, name = "i18n_key_message")
	private String I18nKeyMessage;

	@XmlElement
	@Column(name = "container_flag")
	private Boolean containerFlag;
	
	@XmlElement
	@Column(nullable = false)
	private Boolean hidden;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getHidden() {
		return hidden;
	}

	public void setHidden(Boolean hidden) {
		this.hidden = hidden;
	}

	public String getI18nKeyMessage() {
		return I18nKeyMessage;
	}

	public void setI18nKeyMessage(String i18nKeyMessage) {
		I18nKeyMessage = i18nKeyMessage;
	}

	public boolean isContainerFlag() {
		return containerFlag;
	}

	public void setContainerFlag(boolean containerFlag) {
		this.containerFlag = containerFlag;
	}

	@Override
	public String toString() {
		return "MenuHeader [id=" + id + ", I18nKeyMessage=" + I18nKeyMessage + ", containerFlag=" + containerFlag
				+ ", hidden=" + hidden + "]";
	}
}
