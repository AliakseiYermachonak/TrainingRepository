package com.epam.task.four.taxistation.xmlreader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import com.epam.task.four.taxistation.model.Cab;

public class CabListSAXParser extends DefaultHandler implements XMLParser {
    
    private final static Logger LOGGER = Logger.getLogger(CabListSAXParser.class);
    private List<Cab> cabList = new ArrayList<Cab>();
    private List<Cab> tempCabList = new ArrayList<Cab>();
    private Cab cab;
    private StringBuilder text;

    @Override
    public List<Cab> getCabList() {
        LOGGER.debug("Reading XML and gnerating Cab List with SAX");
        try {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            CabListSAXParser parser = new CabListSAXParser();
            reader.setContentHandler(parser);
            reader.parse(new InputSource(".\\properties\\CabList.xml"));
            cabList = parser.generateCabList();
        } catch (SAXException e) {
            LOGGER.error("SAX went wrong " + e);
        } catch (IOException e) {
            LOGGER.error("IO propblems " + e);
            
        }
        LOGGER.debug("Reading with SAX finished");
        return cabList;
    }

    public List<Cab> generateCabList() {
        return cabList;
    }

    public void startDocument() throws SAXException {
        LOGGER.debug("Parsing started.");
    }

    public void endDocument() throws SAXException {

        cabList.addAll(tempCabList);
        LOGGER.debug("Parsing ended.");
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        text = new StringBuilder();
        if (qName.equals("cab")) {
            cab = new Cab(Integer.parseInt(attributes.getValue("identifier")));
        }
    }

    public void characters(char[] buffer, int start, int length) {
        text.append(buffer, start, length);
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {

        CabListTagName tagName = CabListTagName.valueOf(qName.toUpperCase().replace("-", "_"));
        switch (tagName) {
        case SPEED:
            cab.setSpeed(Integer.parseInt(text.toString()));
            break;
        case FUELCONSUMPTION:
            cab.setFuelConsumption(Integer.parseInt(text.toString()));
            break;
        case PRICE:
            cab.setPrice(Integer.parseInt(text.toString()));
            break;
        case CAB:
            cabList.add(cab);
            cab = null;
            break;
        default:
            break;
        }
    }

    public void warning(SAXParseException exception) {
        LOGGER.error("WARNING: line " + exception.getLineNumber() + ": " + exception.getMessage());
    }

    public void error(SAXParseException exception) {
        LOGGER.error("ERROR: line " + exception.getLineNumber() + ": " + exception.getMessage());
    }
}
