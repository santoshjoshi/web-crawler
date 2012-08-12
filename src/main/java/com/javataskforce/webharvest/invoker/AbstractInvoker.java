package com.javataskforce.webharvest.invoker;

import org.apache.camel.ProducerTemplate;


/**
 * 
 * @author Santosh Joshi
 *
 * @param <T>
 */
public abstract class AbstractInvoker<T>  implements Invoker<T>{

	protected ProducerTemplate template;

	/**
	 * @return the template
	 */
	public ProducerTemplate getTemplate() {
		return template;
	}

	/**
	 * @param template the template to set
	 */
	public void setTemplate(ProducerTemplate template) {
		this.template = template;
	}

}
