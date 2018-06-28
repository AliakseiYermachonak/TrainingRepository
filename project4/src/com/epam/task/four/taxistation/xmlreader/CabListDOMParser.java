package com.epam.task.four.taxistation.xmlreader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.epam.task.four.taxistation.model.Cab;

public class CabListDOMParser implements XMLParser {
	
	private final static Logger LOGGER = Logger.getLogger(CabListDOMParser.class);
	
	private List<Cab> cabList = new ArrayList<Cab>();

	@Override
	public List<Cab> getCabList() {
		LOGGER.debug("Reading XML and gnerating Cab List with DOM");
		DOMParser parser = new DOMParser();
		try {
			parser.parse(".\\properties\\CabList.xml");
		} catch (SAXException | IOException e) {
			LOGGER.error("DOM parser error " + e);
		}
		Document document = parser.getDocument();
		Element root = document.getDocumentElement();
		NodeList cabNodes = root.getElementsByTagName("cab");
		Cab cab = null;
		
		for (int i = 0; i < cabNodes.getLength(); i++) {
		    Element cabElement = (Element) cabNodes.item(i);
		    cab = new Cab(Integer.parseInt(cabElement.getAttribute("identifier")));
		    cab.setSpeed(Integer.parseInt(getSingleChild(cabElement, "speed").getTextContent().trim()));
		    cab.setFuelConsumption(Integer.parseInt(getSingleChild(cabElement, "fuelConsumption").getTextContent().trim()));
		    cab.setPrice(Integer.parseInt(getSingleChild(cabElement, "price").getTextContent().trim()));
		    cabList.add(cab);
		}
		LOGGER.debug("Reading with DOM finished");
		return cabList;
	}
	
	private static Element getSingleChild(Element element, String childName){
	    NodeList nlist = element.getElementsByTagName(childName);
	    Element child = (Element) nlist.item(0);
	    return child;
	}

}
