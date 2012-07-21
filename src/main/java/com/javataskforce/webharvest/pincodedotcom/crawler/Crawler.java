package com.javataskforce.webharvest.pincodedotcom.crawler;

import com.javataskforce.webharvest.pincodedotcom.crawler.impl.DistrictsCrawler;
import com.javataskforce.webharvest.pincodedotcom.crawler.impl.PinCodeCrawler;
import com.javataskforce.webharvest.pincodedotcom.crawler.impl.StatesCrawler;



/**
 * Service for fetching Hotels.
 * 
 * @author Santosh Joshi
 * 
 */

public interface Crawler extends StatesCrawler, DistrictsCrawler, PinCodeCrawler  {

	
}
