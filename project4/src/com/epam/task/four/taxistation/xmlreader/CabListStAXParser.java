package com.epam.task.four.taxistation.xmlreader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.apache.log4j.Logger;

import com.epam.task.four.taxistation.model.Cab;

public class CabListStAXParser implements XMLParser {

    private final static Logger LOGGER = Logger.getLogger(CabListStAXParser.class);
    private List<Cab> cabList = new ArrayList<Cab>();
    private Cab cab;
    private XMLStreamReader reader;
    
    @Override
    public List<Cab> getCabList() {
        LOGGER.debug("Reading XML and gnerating Cab List with StAX");
        try {
            CabListTagName elementName = CabListTagName.CABLIST;
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            reader = inputFactory
                    .createXMLStreamReader(new FileInputStream(".\\properties\\CabList.xml"));
            while (reader.hasNext()) {
                int type = reader.next();
                switch (type) {
                case XMLStreamConstants.START_ELEMENT: {
                    elementName = CabListTagName.getElementTagName(reader.getLocalName());
                    if (elementName == null)
                        continue;
                    if (elementName == CabListTagName.CAB) {
                        Integer id = Integer.parseInt(reader.getAttributeValue(null, "identifier"));
                        cab = new Cab(id);
                        break;
                    }
                    break;
                }
                case XMLStreamConstants.CHARACTERS: {
                    String text = reader.getText().trim();
                    if (text.isEmpty()) {
                        break;
                    }
                    switch (elementName) {
                    case SPEED: {
                        cab.setSpeed(Integer.parseInt(text));
                        break;
                    }
                    case FUELCONSUMPTION: {
                        cab.setFuelConsumption(Integer.parseInt(text));
                        break;
                    }
                    case PRICE: {
                        cab.setPrice(Integer.parseInt(text));
                        break;
                    }
                    default:
                        break;
                    }
                    continue;
                }
                case XMLStreamConstants.END_ELEMENT: {
                    elementName = CabListTagName.getElementTagName(reader.getLocalName());
                    if (elementName == CabListTagName.CAB) {
                        cabList.add(cab);
                    }
                }
                }
            }
            reader.close();
        } catch (NumberFormatException e) {
            LOGGER.error("Something wrong with number " + e);
        } catch (FileNotFoundException e) {
            LOGGER.error("Something wrong with File" + e);
        } catch (XMLStreamException e) {
            LOGGER.error("Something wrong with Exception" + e);
        }
        LOGGER.debug("Reading with StAX finished");
        return cabList;
    }
}
