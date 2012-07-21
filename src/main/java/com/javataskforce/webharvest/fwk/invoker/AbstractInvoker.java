package com.javataskforce.webharvest.fwk.invoker;

import org.apache.camel.ProducerTemplate;

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
