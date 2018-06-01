package com.epam.task.two.text.entity;

import java.util.ArrayList;

public interface Component extends Comparable<Component>{

	/*void parseText(String text, TextType textType);*/
	
	ArrayList<Component> getList();
	
	void add(Component component);
	
	void addAll(ArrayList<Component> components);
	
	boolean isLeaf();
	
	void setLeaf(boolean b);
	
	TextType getTextType();
	
	String getData();
	
	void setData(String data);
	
	int getLength();
	
}