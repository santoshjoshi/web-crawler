package com.javataskforce.webharvest.crawler;

import com.javataskforce.webharvest.crawler.impl.DistrictsCrawler;
import com.javataskforce.webharvest.crawler.impl.PinCodeCrawler;
import com.javataskforce.webharvest.crawler.impl.StatesCrawler;



/**
 * Service for fetching Hotels.
 * 
 * @author Santosh Joshi
 * 
 */

public interface Crawler extends StatesCrawler, DistrictsCrawler, PinCodeCrawler  {

	
}
