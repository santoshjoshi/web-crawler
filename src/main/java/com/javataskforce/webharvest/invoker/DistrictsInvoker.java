package com.javataskforce.webharvest.invoker;

import java.util.ArrayList;
import java.util.List;

import javax.xml.xpath.XPathConstants;

import org.apache.camel.ExchangePattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.javataskforce.webharvest.crawler.impl.DistrictsCrawler;
import com.javataskforce.webharvest.fwk.invoker.AbstractInvoker;
import com.javataskforce.webharvest.fwk.util.CleanHTMLDocument;
import com.javataskforce.webharvest.fwk.util.XPathReader;
import com.javataskforce.webharvest.persistence.entity.EntityStatus;
import com.javataskforce.webharvest.persistence.entity.districts.District;
import com.javataskforce.webharvest.persistence.entity.state.State;

/**
 * 
 * @author Santosh Joshi
 * 
 *
 * a) Invokes the districts Url for a state:
 * b) fetched the page
 * c) applies XPATH expression after cleaning (making it well formed)
 * d) populates the Entity 
 * e) pushes the entities to queue
 */
public class DistrictsInvoker extends AbstractInvoker<State> {
	
	protected final transient static Logger logger = LoggerFactory.getLogger(DistrictsInvoker.class);
 
	public DistrictsCrawler crawler;
	
	/**
	 * @return the crawler
	 */
	public DistrictsCrawler getCrawler() {
		return crawler;
	}

	/**
	 * @param crawler the crawler to set
	 */
	public void setCrawler(DistrictsCrawler crawler) {
		this.crawler = crawler;
	}

	@Override
	public void invokeAndParse(State state) {
 
		String[] params  =state.getInformation().split("\\?") ;
		
		String statesResponse = crawler.getDistricts(params[0], params[1].split("&")[0].replace("statecode=", ""),  params[1].split("&")[1].replace("statename=", ""));
		
		Document document = CleanHTMLDocument.getXHTMLDocument(statesResponse);
		NodeList nodeList= (NodeList) XPathReader.evaluateXPath("/html/body/table[@class='districts']/tr/td[@class='districtslinks']/a", document, XPathConstants.NODESET);
		
		List<District> districts = new ArrayList<District>();
		for (int index = 0; index < nodeList.getLength(); index++) {
			Node aNode = nodeList.item(index);
			NodeList childNodes = aNode.getChildNodes();
			
			NamedNodeMap attributes = aNode.getAttributes();
			String link = attributes.getNamedItem("href").getNodeValue();
			if (childNodes != null && childNodes.item(0) != null) {
				
				District district  = new District();
				district.setStateCode(state.getStateCode()+"");
				district.setEntityStatus(EntityStatus.INITIAL);
				district.setName(childNodes.item(0).getNodeValue());
				district.setInformation(link);
				districts.add(district);
			}
		}
 
		logger.warn("Districts {}", districts.toString());
		template.sendBody("seda:districtStore", ExchangePattern.InOnly, districts);
	}
}
