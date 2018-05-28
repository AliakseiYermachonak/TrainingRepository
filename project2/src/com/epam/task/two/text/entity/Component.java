package com.epam.task.two.text.entity;

import java.util.ArrayList;

public interface Component {

	void parseText(String text, TextType textType);
	
	ArrayList<Component> getList();
	
	void add(Component component);
	
	boolean isLeaf();
	
	void setLeaf(boolean b);
	
	TextType getTextType();
	
	String getData();
	
	void setData(String data);
	
	int getLength();
	
}
