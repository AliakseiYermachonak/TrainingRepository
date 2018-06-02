package com.epam.task.two.text.entity;

/**
 * The enumeration of the type of text.
 * @author Alexey Yermachyonok
 * @version 1.0
 */

public enum TextType {

	TEXT,
	PARAGRAPH,
	SENTENCE,
	WORD;
	
	private int next;
	
	TextType(){
		setNext(this.ordinal() < 3? this.ordinal() + 1 : 3);
	}

	/**
	 * Gets the next type of the text for the component.
	 * @return int - ordinal of the enumeration
	 */
	public int getNext() {
		return next;
	}

	/**
	 * Sets the next type of the text for the component.
	 * @param int - ordinal of the enumeration
	 */
	public void setNext(int next) {
		this.next = next;
	}
		
}
