package com.epam.task.two.text.entity;

import java.util.ArrayList;
import org.apache.log4j.Logger;

/**
 * Component class for realization
 * of Composition pattern.
 * @author Alexey Yermachyonok
 * @version 1.0
 */

public class TextComponent implements Component {

	private static final Logger LOGGER = Logger.getLogger(TextComponent.class);
	
	private ArrayList<Component> list;
	private TextType textType;
	
	public TextComponent() {
		textType = TextType.TEXT;
		LOGGER.debug("creating new text component");
		list = new ArrayList<Component>();
	}

	public TextComponent(ArrayList<Component> list, TextType textType/*, boolean leaf*/) {
		this.list = new ArrayList<Component>();
		this.textType = textType;
		this.list.addAll(list);
		LOGGER.debug("creating new container with " + textType);
	}
	
	/**
	 * Gets list of containing components.
	 * @return ArrayList of the Components
	 */
	@Override
	public ArrayList<Component> getList() {
		return list;
	}

	/**
	 * Adds component to the list of components.
	 * @param Component to add
	 */
	@Override
	public void add(Component component) {
		list.add(component);
	}
	
	/**
	 * Adds list of components to the list of components.
	 * @param ArrayList of the Components
	 */
	@Override
	public void addAll(ArrayList<Component> components) {
		list.addAll(components);
	}

	/**
	 * Shows the text type of a given components.
	 * @return TextType
	 */
	@Override
	public TextType getTextType() {
		return textType;
	}

	/**
	 * Gets the number of element in the list of components.
	 * @return int length.
	 */
	@Override
	public int getLength() {
		return list.size();
	}

	/**
	 * Gets the text of the list of the components.
	 * @return String text.
	 */
	@Override
	public String getData() {
		StringBuilder stringBuilder = new StringBuilder();
		for (Component component: list) {
			switch (component.getTextType()) {
				case TEXT : {
					stringBuilder.append(component.getData());
					break;
				}
				case PARAGRAPH : {
					stringBuilder.append(component.getData());
					stringBuilder.append("\n");	
					break;
				}
				case SENTENCE : {
					String sentence = component.getData();
					stringBuilder.append(sentence);
					if (!sentence.endsWith(" ")) {
						stringBuilder.append(" ");
					}
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

	/**
	 * Writes the text to the component.
	 * @param String text.
	 */
	@Override
	public void setData(String data) {
		LOGGER.debug("You cannot put string data to a not leaf component");
	}
	
	/**
	 * Gets the text of the list of the components.
	 * @return String text.
	 */
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(textType);
		stringBuilder.append(" component contains ");
		stringBuilder.append(list.size());
		stringBuilder.append(" elements.");
		return stringBuilder.toString();
	}

	@Override
	public int compareTo(Component o) {
		return this.getLength()-o.getLength();
	}

}
