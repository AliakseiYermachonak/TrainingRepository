package com.epam.task.two.text.entity;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.epam.task.two.text.exceptions.WTFException;
import com.epam.task.two.text.executors.Parser;

public class TextComponent implements Component{

	private static final Logger logger = Logger.getLogger(TextComponent.class);
	
	static {
		PropertyConfigurator.configure("resources/log4j.properties");
	}
	
	private ArrayList<Component> list;
	private TextType textType;
	private boolean leaf;
	private String data;
	
	public TextComponent(String data) {
		leaf = true;
		textType = TextType.WORD;
		this.data = data;
		logger.debug("creating new leaf component");
		list = new ArrayList();
	}
	
	
	public TextComponent(String text, TextType textType, boolean leaf) {
		this.textType = textType;
		this.leaf = leaf;
		logger.debug("creating new container with " + textType);
		if (!leaf) {
			parseText(text, textType);
		} else {
			this.data = text;
		}
	}

	@Override
	public ArrayList<Component> getList() {
		return list;
	}

	@Override
	public void add(Component component) {
		list.add(component);
	}

	@Override
	public boolean isLeaf() {
		return leaf;
	}
	
	@Override
	public void setLeaf(boolean b) {
		leaf = b;
	}

	@Override
	public TextType getTextType() {
		return textType;
	}

	@Override
	public int getLength() {
		return list.size();
	}
	
	@Override
	public void parseText(String text, TextType textType) {
		try {
			list = Parser.parse(text, textType);
		} catch (WTFException e) {
			logger.error(e);
		}
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
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		if (leaf) {
			return stringBuilder.append(data).toString();
		}
		for (Component component: list) {
			
			switch (component.getTextType()) {
				case TEXT : {
					stringBuilder.append(component.toString());
					break;
				}
				case PARAGRAPH : {
					stringBuilder.append(component.toString());
					stringBuilder.append("\n");	
					break;
				}
				case SENTENCE : {
					stringBuilder.append(component.toString());
					break;
				}
				case WORD : {
					stringBuilder.append(component.getData());
					stringBuilder.append(" ");
					break;
				}
				default: {
					stringBuilder.append(component.toString());
					break;
				}
			}
		}
		return stringBuilder.toString();
	}
}
