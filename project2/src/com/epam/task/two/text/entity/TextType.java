package com.epam.task.two.text.entity;

public enum TextType {

	TEXT("\n"),
	PARAGRAPH("[.]"),
	SENTENCE("[\\\\W_]"),
	PREWORD(" "),
	WORD("");
	
	private String regex;
	private int next;
	
	TextType(String regex){
		this.setRegex(regex);
		setNext(this.ordinal() < 4? this.ordinal() + 1 : 4);
	}

	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}

	public int getNext() {
		return next;
	}

	public void setNext(int next) {
		this.next = next;
	}
	
}
