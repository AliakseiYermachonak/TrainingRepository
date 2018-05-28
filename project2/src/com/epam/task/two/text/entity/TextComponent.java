package com.epam.task.two.text.entity;

import java.util.ArrayList;

public class TextComponent implements Component{

	private ArrayList<Component> list;
	private TextType textType;
	private boolean leaf;
	private String data;
	
	public TextComponent(String data) {
		leaf = true;
		textType = TextType.WORD;
		this.data = data;
		list.add(this);
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
		String[] s1 = text.split(" ");
		for (String s2 : s1) {
			//gag
			list.add(new TextComponent(s2));
		}
	}

	@Override
	public String getData() {
		return null;
	}

	@Override
	public void setData(String data) {
		this.data = data;
	}

}
