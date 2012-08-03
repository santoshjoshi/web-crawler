package com.javataskforce.webharvest.crawler;

import com.javataskforce.webharvest.crawler.impl.DistrictsCrawler;
import com.javataskforce.webharvest.crawler.impl.PinCodeCrawler;
import com.javataskforce.webharvest.crawler.impl.StatesCrawler;



/**
 * Crawl the site and fetch the data
 * 
 * @author Santosh Joshi
 * 
 */

public interface Crawler extends StatesCrawler, DistrictsCrawler, PinCodeCrawler  {

	
}
