package com.javataskforce.webharvest.pincodedotcom.crawler.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;


/**
 * Service for fetching Hotels.
 * 
 * @author Santosh Joshi
 * 
 */

public interface StatesCrawler {

	@POST
	@Produces(value = "text/plain")
	@Consumes(value = "text/plain")
	@Path(value = "/")
	public String getStates();

	 
	
}
