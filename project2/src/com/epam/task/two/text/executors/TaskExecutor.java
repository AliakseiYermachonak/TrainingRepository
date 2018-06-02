package com.epam.task.two.text.executors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.epam.task.two.text.entity.Component;
import com.epam.task.two.text.entity.TextType;
import com.epam.task.two.text.exceptions.ComponentsSearchException;

/**
 * Class container of the static methods
 * for solving the tasks
 * @author Alexey Yermachyonok
 * @version 1.0
 */

public class TaskExecutor {
	
	private static final Logger logger = Logger.getLogger(TaskExecutor.class);
	static {
		PropertyConfigurator.configure("resources/log4j.properties");
	}
	
	/**
	 * Creating a list of the components with given text type
	 * from the source text
	 * @param Component to get the list of the components
	 * @param TextType of the components to find
	 * @return The ArrayList of the components from the given Component list
	 * @see Component
	 */
	public static ArrayList<Component> getTextTypeComponents(Component component, TextType textType)
			throws ComponentsSearchException {
		ArrayList<Component> list = new ArrayList<>();
		if (component.getTextType().compareTo(textType) > 0) {
			throw new ComponentsSearchException();
		} else if (component.getTextType() == textType) {
			
			list.add(component);
		} else if (component.getList() != null) {
			for(Component c: component.getList()) {
				list.addAll(getTextTypeComponents(c, textType));
			}
		}
		return list;
	}
	
	/**
	 * Sorts the list of components by their components capacity
	 * end writes it to the file.
	 * @param Array list of the components to sort,
	 * @see Component
	 */
	public static void componentSort(ArrayList<Component> list) {
			Collections.sort(list);
			for(Component c: list) {
				TextInputOutput.writeToFileTask(c);
			}
	}
	
	/**
	 * Searches for the unique word from the first sentence
	 * end writes it to the file.
	 * @param Array list of the components to sort,
	 */
	public static void uniqueWordSearch(Component component) {
		try {
			ArrayList<Component> list = getTextTypeComponents(component, TextType.SENTENCE);
			Component firstSentence = list.get(0);
			ArrayList<Component> listOfWords = getTextTypeComponents(component, TextType.WORD);
			Map<String, Integer> map = new HashMap<>();
			for (Component c: listOfWords) {
				if(map.containsKey(c.getData())){
					map.put(c.getData(), map.get(c.getData())+1);
				} else {
					map.put(c.getData(), 1);
				}
			}
			ArrayList<Component> listFromFirst = getTextTypeComponents(firstSentence, TextType.WORD);
			for (Component c: listFromFirst) {
				if(map.containsKey(c.getData())&&(map.get(c.getData()) == 1)){
					TextInputOutput.writeToFileTask(c);
				}
			}
		} catch (ComponentsSearchException e) {
			logger.error(e);
		} catch (Exception e) {
			logger.error(e);
		}
	}
}
