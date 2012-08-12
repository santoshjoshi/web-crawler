package com.javataskforce.webharvest.transform;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.javataskforce.webharvest.persistence.entity.EntityStatus;
import com.javataskforce.webharvest.persistence.entity.state.State;

/**
 * 
 * @author Santosh Joshi
 *
 */

public class StateTransformer  {

	protected final transient static Logger log = LoggerFactory	.getLogger(StateTransformer.class);

	public static State toState(com.javataskforce.webharvest.model.state.State result ){
		
		State state = new State();
		state.setCountryCode("IN");
		state.setName(result.getValue());
		state.setEntityStatus(EntityStatus.INITIAL);
		state.setInformation(result.getHref());
		
		return state;
	}
}
