package com.javataskforce.webharvest.persistence.entity.city;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.javataskforce.webharvest.persistence.entity.AbstractConsumableEntity;

/**
 * 
 * @author Santosh Joshi
 * 
 */
@Entity
public class City extends AbstractConsumableEntity {

	@Id
	@Column(name = "cityCode", length = 50)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cityCode;

	@Column(name = "name", nullable = false, length = 128)
	private String name;

	@Column(name = "districtCode", length = 50)
	private String districtCode;
	
	@Column(name = "stateCode", length = 50)
	private String stateCode;
	
	@Column(name = "latitude", length = 50)
	private String latitude;

	@Column(name = "longitude", length = 50)
	private String longitude;
	
	@Column(name = "pinCode", length = 50)
	private String pincode;
	
	/**
	 * @return the cityCode
	 */
	public Long getCityCode() {
		return cityCode;
	}

	/**
	 * @param cityCode the cityCode to set
	 */
	public void setCityCode(Long cityCode) {
		this.cityCode = cityCode;
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
	 * @return the pincode
	 */
	public String getPincode() {
		return pincode;
	}

	/**
	 * @param pincode the pincode to set
	 */
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	 

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	 

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cityCode == null) ? 0 : cityCode.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		City other = (City) obj;
		if (cityCode == null) {
			if (other.cityCode != null)
				return false;
		} else if (!cityCode.equals(other.cityCode))
			return false;
		 
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	/**
	 * @return the districtCode
	 */
	public String getDistrictCode() {
		return districtCode;
	}

	/**
	 * @param districtCode the districtCode to set
	 */
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}
	
}
