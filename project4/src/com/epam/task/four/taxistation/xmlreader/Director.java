package com.epam.task.four.taxistation.xmlreader;

import java.util.List;

import com.epam.task.four.taxistation.model.Cab;

public class Director {
	
	XMLParserBuilder builder = new XMLParserBuilder(); 
	
	public List<Cab> useSAX() {
		return builder.getSAXParser().getCabList();
	}
	
	public List<Cab> useStAX() {
		return builder.getStAXParser().getCabList();
	}
	
	public List<Cab> useDOM() {
		return builder.getDOMParser().getCabList();
	}
	
}
