package com.javataskforce.webharvest.persistence.entity;

/**
 * @author santosh joshi
 *
 */
public interface Consumable {

	/**
	 * when and entity is consumed its moved to next status 
	 */
	void consume();
}
