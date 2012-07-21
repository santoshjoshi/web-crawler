package com.javataskforce.webharvest.persistence.entity.state;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.javataskforce.webharvest.persistence.entity.AbstractConsumableEntity;

/**
 * 
 * @author santoshjo
 * 
 */
@Entity
public class State extends AbstractConsumableEntity {

	@Id
	@Column(name = "stateCode", length = 50)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long stateCode;

	@Column(name = "name", nullable = false, length = 128)
	private String name;
	
	@Column(name = "info", nullable = false, length = 128)
	private String information;

	@Column(name = "countryCode", nullable = false, length = 8)
	private String countryCode;

	/**
	 * @return the stateCode
	 */
	public Long getStateCode() {
		return stateCode;
	}

	/**
	 * @param stateCode the stateCode to set
	 */
	public void setStateCode(Long stateCode) {
		this.stateCode = stateCode;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the countryCode
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * @param countryCode the countryCode to set
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	/**
	 * @return the information
	 */
	public String getInformation() {
		return information;
	}

	/**
	 * @param information the information to set
	 */
	public void setInformation(String information) {
		this.information = information;
	}
}
