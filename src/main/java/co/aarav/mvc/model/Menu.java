package co.aarav.mvc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
@Entity(name = "menu")
public class Menu {

	@XmlElement
	@Id
	@GeneratedValue
	private Long id;

	@XmlElement
	@Column(length = 50, name = "i18n_key_message")
	private String I18nKeyMessage;

	@XmlElement
	@ManyToOne
	@JoinColumn(name = "menu_header_id")
	private MenuHeader menuHeader;


	@XmlElement
	@Column(name = "container_flag")
	private Boolean containerFlag;

	public Boolean getContainerFlag() {
		return containerFlag;
	}

	public void setContainerFlag(Boolean containerFlag) {
		this.containerFlag = containerFlag;
	}

	@XmlElement
	@Column(nullable = false)
	private Boolean hidden;
	
	
	@XmlElement
	@Column
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public MenuHeader getMenuHeader() {
		return menuHeader;
	}

	public void setMenuHeader(MenuHeader menuHeader) {
		this.menuHeader = menuHeader;
	}

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
		return "Menu [id=" + id + ", I18nKeyMessage=" + I18nKeyMessage + ", menuHeader=" + menuHeader
				+ ", containerFlag=" + containerFlag + ", hidden=" + hidden + ", url=" + url + "]";
	}
}
