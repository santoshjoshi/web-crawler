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

import com.javataskforce.webharvest.crawler.impl.StatesCrawler;
import com.javataskforce.webharvest.fwk.invoker.AbstractInvoker;
import com.javataskforce.webharvest.fwk.util.CleanHTMLDocument;
import com.javataskforce.webharvest.fwk.util.XPathReader;
import com.javataskforce.webharvest.persistence.entity.EntityStatus;
import com.javataskforce.webharvest.persistence.entity.state.State;

/**
 * 
 * @author Santosh Joshi
 * 
 *
 * a) Invokes the states Url for a country :
 * b) fetched the page
 * c) applies XPATH expression after cleaning (making it well formed)
 * d) populates the Entity 
 * e) pushes the entities to queue
 */
public class StatesInvoker extends AbstractInvoker<Object> {
	
	protected final transient static Logger logger = LoggerFactory.getLogger(StatesInvoker.class);
 
	private StatesCrawler crawler;
	 
	/**
	 * @return the crawler
	 */
	public StatesCrawler getCrawler() {
		return crawler;
	}
	/**
	 * @param crawler the crawler to set
	 */
	public void setCrawler(StatesCrawler crawler) {
		this.crawler = crawler;
	}


	@Override
	public void invokeAndParse(Object object) {
 
		String statesResponse = crawler.getStates();
		Document document = CleanHTMLDocument.getXHTMLDocument(statesResponse);
		NodeList nodeList= (NodeList) XPathReader.evaluateXPath("/html/body/table[@class='states']/tr/td[@class='stateslink']/a", document, XPathConstants.NODESET);
		
		List<State> states = new ArrayList<State>();
		
		for (int index = 0; index < nodeList.getLength(); index++) {
			Node aNode = nodeList.item(index);
			
			NamedNodeMap attributes = aNode.getAttributes();
			
			String link = attributes.getNamedItem("href").getNodeValue();
			if(link != null){
				NodeList childNodes = aNode.getChildNodes();
				if(childNodes != null){
					
					for (int i = 0; i < childNodes.getLength(); i++) {
						Node childNode = childNodes.item(i);
						if(childNode != null){
							if(childNode.getNodeType() == Node.TEXT_NODE ){
								logger.warn("Key {} --> value {} ",link, childNode.getNodeValue() );
								 
									State state = new State();
									state.setCountryCode("In");
									state.setName(childNode.getNodeValue());
									state.setInformation(link);
									state.setEntityStatus(EntityStatus.INITIAL);
									states.add(state);
							}
						}
					}
				}
			}
		}
		logger.warn("States {}",  states);
		template.sendBody("seda:statesStore", ExchangePattern.InOnly, states);
	}
}
