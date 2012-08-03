package com.javataskforce.webharvest.persistence.entity;

/**
 * 
 * @author Santosh Joshi
 *
 * EntityStatus is used to track the state of and entity
 * 
 * Initially an entity has an {@linkplain EntityStatus#INITIAL} status: The status becomes in {@linkplain EntityStatus#IN_PROCESS}
 * when it is being crawled  and becomes {@linkplain EntityStatus#COMPLETED}  when the crawl is complete
 *  
 *
 */
public enum EntityStatus {

	INITIAL {
		
		@Override 
		public EntityStatus nextStatus() {
			return IN_PROCESS;
		}
	},
	
	IN_PROCESS {
		
		@Override
		public EntityStatus nextStatus() {
			return COMPLETED;  
		}
	},
	COMPLETED{
		
		@Override
		public EntityStatus nextStatus() {
			return COMPLETED;
		}
	},
	FAILED{
		
		@Override
		public EntityStatus nextStatus() {
			return FAILED;
		}
	};
	
	public abstract EntityStatus nextStatus();
}
