package com.epam.task.four.taxistation.xmlreader;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

public class XMLValidator {
    
    final static Logger LOGGER = Logger.getLogger(XMLValidator.class);
    
    public static boolean validateList() {
        try {
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(new File("properties\\CabList.xsd"));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File("properties\\CabList.xml")));
            return true;
        } catch (SAXException e) {
            LOGGER.error("XSD validation failed " + e);
        } catch (IOException e) {
            LOGGER.error("IOException during validation " + e);
        }
        LOGGER.error("XML file is not XSD valid");
        return false;
    }

}
