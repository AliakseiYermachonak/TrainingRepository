package com.epam.task.two.text.entity;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.epam.task.two.text.exception.ComponentMismatchException;

public class LeafComponent implements Component{
	
	private static final Logger LOGGER = Logger.getLogger(LeafComponent.class);
	
	private ArrayList<Component> list;
	private String data;

	public LeafComponent(String text) {
		LOGGER.debug("creating new leaf component");
		list = new ArrayList<Component>();
		data = text;
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
		try {
			throw new ComponentMismatchException();
		} catch (ComponentMismatchException e) {
			LOGGER.error("Cannot add Component into the leaf");
			e.printStackTrace();
		}
		LOGGER.debug("cannot add Component into the leaf");
	}

	@Override
	public void addAll(ArrayList<Component> components) {
		try {
			throw new ComponentMismatchException();
		} catch (ComponentMismatchException e) {
			LOGGER.error("Cannot add Components into the leaf");
			e.printStackTrace();
		}
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

}
