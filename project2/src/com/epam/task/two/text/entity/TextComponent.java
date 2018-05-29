package com.epam.task.two.text.entity;

import java.util.ArrayList;

import com.epam.task.two.text.executors.Parser;

public class TextComponent implements Component{

	private ArrayList<Component> list;
	private TextType textType;
	private boolean leaf;
	private String data;
	
	public TextComponent(String data) {
		leaf = true;
		textType = TextType.WORD;
		this.data = data;
		//list.add(this);
	}
	
	
	public TextComponent(String text, TextType textType) {
		this.textType = textType;
		leaf = false;
		data = "";
		parseText(text, textType);
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
		list = Parser.parse(text, textType);
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
					stringBuilder.append(". ");
					break;
				}
				case PREWORD :{
					stringBuilder.append(component.toString());
					break;
				}
				case WORD :{
					stringBuilder.append(component.getData());
					break;
				}
				default: {
					
				}
			}
			
			/*if (component.isLeaf()) {
				stringBuilder.append(component.getData());
			} else {
				stringBuilder.append(component.toString());
			}*/
		}
		return stringBuilder.toString();
	}
}
