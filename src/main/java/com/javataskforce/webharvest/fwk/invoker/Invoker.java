package com.javataskforce.webharvest.fwk.invoker;


/**
 * 
 * @author Santosh Joshi
 *
 */
public interface Invoker<T> {
	
	/**
	 * Invokes a service by taking necessary information from the provided Object
	 * 
	 * @param Object
	 */
	public void invokeAndParse( T object);

}
