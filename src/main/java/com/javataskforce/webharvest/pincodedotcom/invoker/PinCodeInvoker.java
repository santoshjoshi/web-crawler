package com.javataskforce.webharvest.pincodedotcom.invoker;

import java.util.ArrayList;
import java.util.List;

import javax.xml.xpath.XPathConstants;

import org.apache.camel.ExchangePattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.javataskforce.webharvest.fwk.invoker.AbstractInvoker;
import com.javataskforce.webharvest.fwk.util.CleanHTMLDocument;
import com.javataskforce.webharvest.fwk.util.XPathReader;
import com.javataskforce.webharvest.persistence.entity.EntityStatus;
import com.javataskforce.webharvest.persistence.entity.city.City;
import com.javataskforce.webharvest.persistence.entity.districts.District;
import com.javataskforce.webharvest.pincodedotcom.crawler.impl.PinCodeCrawler;

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
 
		String statesResponse = crawler.getPinCodes(district.getInformation().split("/")[3],district.getInformation().split("/")[4]);
		
		Document document = CleanHTMLDocument.getXHTMLDocument(statesResponse);
		NodeList nodeList= (NodeList) XPathReader.evaluateXPath("//table[class='plist']/tbody/tr", document, XPathConstants.NODESET);
		
		List<City>  cities = new ArrayList<City>(300);
		
		for (int index = 0; index < nodeList.getLength(); index++) {
			Node aNode = nodeList.item(index);
			NodeList childNodes = aNode.getChildNodes();
			if (childNodes != null) {
				
				City city = new City();
				city.setEntityStatus(EntityStatus.COMPLETED);
				city.setName(childNodes.item(0).getChildNodes().item(1).getNodeValue());
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
