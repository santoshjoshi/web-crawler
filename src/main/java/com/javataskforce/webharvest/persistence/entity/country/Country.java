package com.javataskforce.webharvest.persistence.entity.country;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.javataskforce.webharvest.persistence.entity.AbstractConsumableEntity;


@Entity
public class Country extends AbstractConsumableEntity {

	@Id
	@Column(name = "countryCode", length = 20)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long countryCode;

	@Column(name = "languagecode", length = 8)
	private String languagecode;

	@Column(name = "name", nullable = false)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(Long countryCode) {
		this.countryCode = countryCode;
	}

	public String getLanguagecode() {
		return languagecode;
	}

	public void setLanguagecode(String languagecode) {
		this.languagecode = languagecode;
	}

	@Override
	public String toString() {
		return "Country [name=" + name + ", countryCode=" + countryCode + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((countryCode == null) ? 0 : countryCode.hashCode());
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
		Country other = (Country) obj;
		if (countryCode == null) {
			if (other.countryCode != null)
				return false;
		} else if (!countryCode.equals(other.countryCode))
			return false;
		return true;
	}
}
