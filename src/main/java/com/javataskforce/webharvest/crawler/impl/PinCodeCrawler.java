package com.javataskforce.webharvest.crawler.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;


/**
 * Service for fetching Pin codes.
 * 
 * @author Santosh Joshi
 * 
 */

public interface PinCodeCrawler {

	@POST
	@Produces(value = "text/plain")
	@Consumes(value = "text/plain")
	@Path(value = "/{pageurl}")
	public String getPinCodes(@PathParam("pageurl") String pageUrl,@QueryParam("districtcode") String districtCode);
	
}
