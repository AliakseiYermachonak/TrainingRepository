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
	
	public TextComponent() {
		LOGGER.debug("creating new text component");
		list = new ArrayList<Component>();
	}

	public TextComponent(ArrayList<Component> list) {
		this.list = new ArrayList<Component>();
		this.list.addAll(list);
		LOGGER.debug("creating new container ");
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
			if (component instanceof LeafComponent) {
				stringBuilder.append(component.getData());
				if (!component.getData().endsWith("\n")) {
					stringBuilder.append(" ");
				}
				continue;
			} else {
				stringBuilder.append(component.getData());
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
		stringBuilder.append(this.getClass().getSimpleName());
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
