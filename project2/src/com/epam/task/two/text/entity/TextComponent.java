package com.epam.task.two.text.entity;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class TextComponent implements Component {

	private static final Logger logger = Logger.getLogger(TextComponent.class);
	
	static {
		PropertyConfigurator.configure("resources/log4j.properties");
	}
	
	private ArrayList<Component> list;
	private TextType textType;
	private boolean leaf;
	private String data;
	
	public TextComponent() {
		leaf = false;
		textType = TextType.TEXT;
		logger.debug("creating new text component");
		list = new ArrayList<Component>();
	}
	
	public TextComponent(String data) {
		leaf = true;
		textType = TextType.WORD;
		this.data = data;
		logger.debug("creating new leaf component");
		list = new ArrayList<Component>();
	}
	
	public TextComponent(String text, TextType textType, boolean leaf) {
		this.list = new ArrayList<Component>();
		this.textType = textType;
		this.leaf = leaf;
		logger.debug("creating new container with " + textType);
		this.data = text;
	}

	public TextComponent(ArrayList<Component> list, TextType textType, boolean leaf) {
		this.list = new ArrayList<Component>();
		this.textType = textType;
		this.list.addAll(list);
		this.leaf = leaf;
		logger.debug("creating new container with " + textType);
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
	public void addAll(ArrayList<Component> components) {
		list.addAll(components);
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
	public String getData() {
		if (leaf) {
			return data;
		} else {
			return this.toString();
		}
	}

	@Override
	public void setData(String data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		if (leaf) {
			String s = stringBuilder.append(data).toString();
			return s;
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
					String sentence = component.toString();
					stringBuilder.append(sentence);
					if (!sentence.endsWith(" ")) {
						stringBuilder.append(" ");
					}
					break;
				}
				case WORD : {
					stringBuilder.append(component.getData());
					/*if (component.getData().matches("[,.!?]")) {
						stringBuilder.deleteCharAt(stringBuilder.length()-2);
						System.out.println(stringBuilder);
					}*/
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

	@Override
	public int compareTo(Component o) {
		return this.getLength()-o.getLength();
	}

}
