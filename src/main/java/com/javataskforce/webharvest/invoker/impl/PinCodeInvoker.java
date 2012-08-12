package com.javataskforce.webharvest.invoker.impl;

import java.util.ArrayList;
import java.util.List;

import javax.xml.xpath.XPathConstants;

import org.apache.camel.ExchangePattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.javataskforce.webharvest.crawler.impl.PinCodeCrawler;
import com.javataskforce.webharvest.invoker.AbstractInvoker;
import com.javataskforce.webharvest.persistence.entity.EntityStatus;
import com.javataskforce.webharvest.persistence.entity.city.City;
import com.javataskforce.webharvest.persistence.entity.districts.District;
import com.javataskforce.webharvest.util.CleanHTMLDocument;
import com.javataskforce.webharvest.util.XPathReader;

/**
 * 
 * @author Santosh Joshi
 * 
 *
 * a) Invokes the pincodes Url for a district:
 * b) fetched the page
 * c) applies XPATH expression after cleaning (making it well formed)
 * d) populates the Entity 
 * e) pushes the entities to queue
 */
public class PinCodeInvoker extends AbstractInvoker<District> {
	
	protected final transient static Logger logger = LoggerFactory.getLogger(PinCodeInvoker.class);
 
	private PinCodeCrawler crawler;

	/**
	 * @return the crawler
	 */
	public PinCodeCrawler getCrawler() {
		return crawler;
	}

	/**
	 * @param crawler the crawler to set
	 */
	public void setCrawler(PinCodeCrawler crawler) {
		this.crawler = crawler;
	}

	@Override
	public void invokeAndParse(District district) {
 
		String[] info = district.getInformation().split("\\?");
		String statesResponse = crawler.getPinCodes(info[0],info[1].split("=")[1]);
		
		Document document = CleanHTMLDocument.getXHTMLDocument(statesResponse);
		NodeList nodeList= (NodeList) XPathReader.evaluateXPath("/html/body/table[@class='areas']/tr/td[@class='pincodes']", document, XPathConstants.NODESET);
		
		List<City>  cities = new ArrayList<City>(300);
		
		for (int index = 0; index < nodeList.getLength(); index++) {

			NodeList childNodes =  nodeList.item(index).getChildNodes();
				if (childNodes != null && childNodes.item(0) != null && childNodes.item(1) != null) {
					
					City city = new City();
					city.setEntityStatus(EntityStatus.COMPLETED);
					city.setName(childNodes.item(0).getChildNodes().item(0).getNodeValue());
					city.setPincode(childNodes.item(1).getChildNodes().item(0).getNodeValue());
					city.setStateCode(district.getStateCode());
					city.setDistrictCode(district.getCode()+"");
					cities.add(city);
				}
			}
		logger.warn("CITIES  {}", cities.toString());
		template.sendBody("seda:pincodeStore", ExchangePattern.InOnly, cities);
	}
}
