package com.epam.task.two.text.executors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.epam.task.two.text.entity.Component;
import com.epam.task.two.text.entity.TextType;
import com.epam.task.two.text.exceptions.WTFException;

public class TaskExecutor {
	
	private static final Logger logger = Logger.getLogger(TaskExecutor.class);
	static {
		PropertyConfigurator.configure("resources/log4j.properties");
	}
	
	public static ArrayList<Component> getTextTypeComponents(Component component, TextType textType) throws WTFException {
		ArrayList<Component> list = new ArrayList<>();
		if (component.getTextType().compareTo(textType) > 0) {
			throw new WTFException();
		} else if (component.getTextType() == textType) {
			
			list.add(component);
		} else if (component.getList() != null) {
			for(Component c: component.getList()) {
				list.addAll(getTextTypeComponents(c, textType));
			}
		}
		return list;
	}
	
	public static void componentSort(ArrayList<Component> list) {
			Collections.sort(list);
			for(Component c: list) {
				TextInputOutput.writeToFileTask(c);
			}
	}
	
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
		} catch (WTFException e) {
			logger.error(e);
		} catch (Exception e) {
			logger.error(e);
		}
	}
}
