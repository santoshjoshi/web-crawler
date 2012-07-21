package com.javataskforce.webharvest.pincodedotcom.invoker;

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

import com.javataskforce.webharvest.fwk.invoker.AbstractInvoker;
import com.javataskforce.webharvest.fwk.util.CleanHTMLDocument;
import com.javataskforce.webharvest.fwk.util.XPathReader;
import com.javataskforce.webharvest.persistence.entity.EntityStatus;
import com.javataskforce.webharvest.persistence.entity.districts.District;
import com.javataskforce.webharvest.persistence.entity.state.State;
import com.javataskforce.webharvest.pincodedotcom.crawler.impl.DistrictsCrawler;

/**
 * 
 * @author santosh
 *
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
 
		String statesResponse = crawler.getDistricts(state.getInformation().split("/")[3]);
		
		Document document = CleanHTMLDocument.getXHTMLDocument(statesResponse);
		NodeList nodeList= (NodeList) XPathReader.evaluateXPath("//table[class='plist']/tbody/tr", document, XPathConstants.NODESET);
		
		List<District> districts = new ArrayList<District>();
		
		for (int index = 0; index < nodeList.getLength(); index++) {
			Node aNode = nodeList.item(index);
			NodeList childNodes = aNode.getChildNodes();

			if(childNodes != null){
				
				for (int i = 0; i < childNodes.getLength(); i++) {
					
					Node childNode = childNodes.item(i);
					if("td".equalsIgnoreCase(childNode.getNodeName()) ){
						NamedNodeMap attributes = aNode.getAttributes();
						
						String totalTd = attributes.getNamedItem("colspan").getNodeValue();
						if(totalTd != null && 3< Integer.parseInt(totalTd)){
							continue;
						}
						for (int j = 0; j < childNode.getChildNodes().getLength(); j++) {
							Node deepChildNode = childNode.getChildNodes().item(j);
							if(deepChildNode != null){
								String link = deepChildNode.getAttributes().getNamedItem("href").getNodeValue();
								if(childNode.getNodeType() == Node.TEXT_NODE ){
									logger.warn("Key {} --> value {} ",link, childNode.getNodeValue() );
									
									District district  = new District();
									district.setStateCode(state.getStateCode()+"");
									district.setEntityStatus(EntityStatus.INITIAL);
									district.setName(childNode.getNodeValue());
									district.setInformation(link);
									districts.add(district);
								}
							}
						}
					}
				}
			}
		}
		logger.warn("Districts {}", districts.toString());
		template.sendBody("seda:districtStore", ExchangePattern.InOnly, districts);
	}
}
