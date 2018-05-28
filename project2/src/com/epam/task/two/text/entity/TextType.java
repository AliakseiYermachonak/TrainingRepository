package com.epam.task.two.text.entity;

public enum TextType {

	TEXT("zero"),
	PARAGRAPH("one"),
	SENTENCE("two"),
	WORD("three");
	
	private String regex;
	private int next;
	
	TextType(String regex){
		this.setRegex(regex);
		setNext(this.ordinal() < 3? this.ordinal() + 1 : 3);
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
