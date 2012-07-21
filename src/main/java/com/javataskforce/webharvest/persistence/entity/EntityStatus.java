package com.javataskforce.webharvest.persistence.entity;

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
