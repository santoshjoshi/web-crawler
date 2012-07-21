package com.javataskforce.webharvest.persistence.entity.districts;

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
public class District extends AbstractConsumableEntity {

	@Id
	@Column(name = "code", length = 50)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long code;

	@Column(name = "name", nullable = false, length = 128)
	private String name;

	@Column(name = "stateCode", length = 50)
	private String stateCode;
	
	@Column(name = "latitude", length = 50)
	private String latitude;

	@Column(name = "longitude", length = 50)
	private String longitude;

	@Column(name = "info", nullable = false, length = 128)
	private String information;
	
	/**
	 * @return the code
	 */
	public Long getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(Long code) {
		this.code = code;
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
	 * @return the stateCode
	 */
	public String getStateCode() {
		return stateCode;
	}

	/**
	 * @param stateCode the stateCode to set
	 */
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	/**
	 * @return the latitude
	 */
	public String getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the longitude
	 */
	public String getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "District [name=" + name + ", stateCode=" + stateCode
				+ ", information=" + information + "]";
	}
	
	 
}
