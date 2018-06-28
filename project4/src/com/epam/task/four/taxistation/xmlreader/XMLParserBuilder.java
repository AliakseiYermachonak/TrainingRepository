package com.epam.task.four.taxistation.xmlreader;

public class XMLParserBuilder {
	
	public XMLParser getDOMParser() {
		return new CabListDOMParser();
	}
	
	public XMLParser getSAXParser() {
		return new CabListSAXParser();
	}
	
	public XMLParser getStAXParser() {
		return new CabListStAXParser();
	}
	
}
