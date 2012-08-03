package com.javataskforce.webharvest.persistence.entity;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

/**
 * @author Santosh Joshi
 *
 */
@MappedSuperclass
public abstract class AbstractConsumableEntity extends AbstractEntity implements Consumable {

	@Column(name = "remarks")
	private String remarks;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "entityStatus", nullable = false)
	private EntityStatus entityStatus;
	
	
	public EntityStatus getEntityStatus() {
		return entityStatus;
	}

	public void setEntityStatus(EntityStatus entityStatus) {
		this.entityStatus = entityStatus;
	}
	
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	@Override
	public void consume() {
		entityStatus = entityStatus.nextStatus();
	}
}
