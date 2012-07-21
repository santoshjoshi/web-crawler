package com.javataskforce.webharvest.persistence.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author santosh 
 *
 */
@MappedSuperclass
public abstract class AbstractEntity {

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createDate")
	private Date createDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updateDate")
	private Date updateDate;

	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @return the updateDate
	 */
	public Date getUpdateDate() {
		return updateDate;
	}
	
	@PrePersist
	public void prePersist() {
		createDate = new Date();
	}
	
	@PreUpdate
	public void preUpdate() {
		updateDate = new Date();
	}

	/**
	 * @param updateDate the updateDate to set
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	
}
