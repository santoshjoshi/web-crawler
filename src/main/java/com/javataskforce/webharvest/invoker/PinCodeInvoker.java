package com.javataskforce.webharvest.invoker;

import java.util.ArrayList;
import java.util.List;

import javax.xml.xpath.XPathConstants;

import org.apache.camel.ExchangePattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.javataskforce.webharvest.crawler.impl.PinCodeCrawler;
import com.javataskforce.webharvest.fwk.invoker.AbstractInvoker;
import com.javataskforce.webharvest.fwk.util.CleanHTMLDocument;
import com.javataskforce.webharvest.fwk.util.XPathReader;
import com.javataskforce.webharvest.persistence.entity.EntityStatus;
import com.javataskforce.webharvest.persistence.entity.city.City;
import com.javataskforce.webharvest.persistence.entity.districts.District;

/**
 * 
 * @author santosh
 *
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
		NodeList nodeList= (NodeList) XPathReader.evaluateXPath("//body//table/tr", document, XPathConstants.NODESET);
		
		List<City>  cities = new ArrayList<City>(300);
		
		for (int index = 0; index < nodeList.getLength(); index++) {
			
			
			//for (int j = 0; index < childNodes.getLength(); j++) {
				//NodeList childs = aNode.getChildNodes();
				NodeList childNodes =  nodeList.item(index).getChildNodes();
				if (childNodes != null) {
					
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
