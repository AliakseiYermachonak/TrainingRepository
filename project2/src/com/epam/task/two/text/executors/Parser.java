package com.epam.task.two.text.executors;

import java.util.ArrayList;

import com.epam.task.two.text.entity.Component;
import com.epam.task.two.text.entity.TextComponent;
import com.epam.task.two.text.entity.TextType;

public class Parser {

	public static ArrayList<Component> parse(String text, TextType textType) {
		
		StringBuilder listing = new StringBuilder();
		String[] partsOfText = text.split(textType.getRegex());
		ArrayList<Component> list = new ArrayList<>();
				
		boolean listeningListing = false;
		for(String part : partsOfText) {
			part.trim();
			if (textType.ordinal() < (TextType.values().length - 1)) {
				if (isStartOfListing(part)||listeningListing) {
					if (isEndOfListing(part)) {
						listing.append(part);
						list.add(new TextComponent(listing.toString()));
						//System.out.println(listing.toString());
						listing = new StringBuilder();
						listeningListing = false;
					} else {
						listing.append(part);
						listing.append("\n");
						listeningListing = true;
					}
				} else {
					list.add(new TextComponent(part, TextType.values()[textType.getNext()]));
				}
			} else {
				list.add(new TextComponent(part));
			}
		}
		return list;
	}
	
	public static boolean isStartOfListing(String part) {
		return part.matches("[a-z_].+{0,80}\\{");
	}
	
	public static boolean isEndOfListing(String part) {
		return part.matches("\\}");
	}
	
}					
