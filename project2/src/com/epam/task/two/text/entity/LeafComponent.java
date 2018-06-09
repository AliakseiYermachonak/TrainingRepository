package com.epam.task.two.text.entity;

import java.util.ArrayList;

import org.apache.log4j.Logger;

public class LeafComponent implements Component{
	
	private static final Logger LOGGER = Logger.getLogger(LeafComponent.class);
	
	private ArrayList<Component> list;
	private String data;
	private TextType textType;

	public LeafComponent(String text, TextType textType) {
		LOGGER.debug("creating new leaf component");
		list = new ArrayList<Component>();
		data = text;
		this.textType = textType;
	}

	@Override
	public int compareTo(Component o) {
		return this.getLength()-o.getLength();
	}

	@Override
	public ArrayList<Component> getList() {
		return list;
	}

	@Override
	public void add(Component component) {
		LOGGER.debug("cannot add Component into the leaf");
	}

	@Override
	public void addAll(ArrayList<Component> components) {
		LOGGER.debug("cannot add Components into the leaf");
	}

	@Override
	public String getData() {
		return data;
	}

	@Override
	public void setData(String data) {
		this.data = data;
	}

	@Override
	public int getLength() {
		return list.size();
	}

	@Override
	public TextType getTextType() {
		return this.textType;
	}

}
