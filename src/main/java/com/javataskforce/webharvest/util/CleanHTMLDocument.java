package com.javataskforce.webharvest.util;

import java.io.ByteArrayInputStream;
import java.io.CharArrayWriter;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.tidy.Tidy;
/**
 * 
 * @author Santosh Joshi
 * 
 * CLeaning of HTML document :
 * 	making it well formed(XHTML)  
 *
 */
public class CleanHTMLDocument {

	/**
	 * The function returns the XHTML compliment document. It uses Tidy to
	 * create an XHTML document.
	 * 
	 * @param sourceString - XHTML document is created of this string
	 * @return the well formed Document Object
	 * @throws PublishException
	 */

	public static Document getXHTMLDocument(String sourceString){
		Tidy tidy = new Tidy();

		tidy.setBreakBeforeBR(true);
		tidy.setUpperCaseTags(false);
		tidy.setUpperCaseAttrs(false);
		tidy.setShowWarnings(false);

		return tidy.parseDOM(new ByteArrayInputStream(sourceString.getBytes()), null);
	}

	/**
	 * This method outputs a well formed document out of a DOM object This
	 * 
	 * @param document the document which is to be converted into a string
	 */
	public static String writeXHTMLDocument(Document document)
	{
		String xmlString = null ;
		try
		{
			TransformerFactory factory = TransformerFactory.newInstance();
			CharArrayWriter writer = new CharArrayWriter(1024);
			try	{
				Transformer transformer = factory.newTransformer();
				transformer.setOutputProperty("indent", "yes");

				DOMSource src = new DOMSource(document);
				StreamResult rs = new StreamResult(writer);
				transformer.transform(src, rs);

				xmlString = writer.toString();

			}
			catch (Exception e) {
				e.printStackTrace();
			}
			finally{
				writer.close();
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		return xmlString;
	}
	
}
