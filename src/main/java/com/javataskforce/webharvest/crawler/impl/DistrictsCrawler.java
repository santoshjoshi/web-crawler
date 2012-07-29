package com.javataskforce.webharvest.crawler.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;


/**
 * Service for fetching Districts.
 * 
 * @author Santosh Joshi
 * 
 */

public interface DistrictsCrawler {

	@POST
	@Produces(value = "text/plain")
	@Consumes(value = "text/plain")
	@Path(value = "/{statePage}")
	public String getDistricts(@PathParam("statePage") String state, @QueryParam("statecode") String stateCode, @QueryParam("statename") String stateName );
	
}
