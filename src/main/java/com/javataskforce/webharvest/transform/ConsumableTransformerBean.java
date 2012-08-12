package com.javataskforce.webharvest.transform;

import com.javataskforce.webharvest.persistence.entity.Consumable;
import com.javataskforce.webharvest.persistence.entity.EntityStatus;

/**
 * @author Santosh Joshi
 *
 * whenever an entity is consumed its status is moved to next level:
 * 
 * 	Life cycle of an entity 
 * 		{@link EntityStatus#INITIAL}   
 * 			---> {@link EntityStatus#IN_PROCESS} 	
 * 				---> {@link EntityStatus#COMPLETED}
 * 			---> {@link EntityStatus#FAILED}	
 * 
 */
public class ConsumableTransformerBean {

	public void consume(Consumable consumable) {
		consumable.consume();
	}
}
