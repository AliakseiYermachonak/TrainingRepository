package com.epam.task.two.text.entity;

public enum TextType {

	TEXT("\n"),
	PARAGRAPH("\\d.*?[\\s]+|\\b\\p{Upper}.*?[!.?]+|\\b\\p{Upper}.*?$"),
	SENTENCE("\\d.*?[.][$]|\\p{Graph}.*?[!.?:\\s]+|\\p{Graph}.*?$"),
	WORD("\\w+|[!.?:]+");
	
	private String regex;
	private int next;
	public final static String PREWORD = "[a-zA-Z]*?[,!.?:]+";
	
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
