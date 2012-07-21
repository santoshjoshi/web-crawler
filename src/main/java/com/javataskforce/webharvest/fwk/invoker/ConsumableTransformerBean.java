package com.javataskforce.webharvest.fwk.invoker;

import com.javataskforce.webharvest.persistence.entity.Consumable;

/**
 * @author santoshjoshi
 *
 */
public class ConsumableTransformerBean {

	public void consume(Consumable consumable) {
		consumable.consume();
	}
}
