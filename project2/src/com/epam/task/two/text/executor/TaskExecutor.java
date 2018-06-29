package com.epam.task.two.text.executor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.epam.task.two.text.entity.Component;
import com.epam.task.two.text.exception.ComponentMismatchException;

/**
 * Class container of the static methods for solving the tasks
 * 
 * @author Alexey Yermachyonok
 * @version 1.0
 */

public class TaskExecutor {

    private static final Logger LOGGER = Logger.getLogger(TaskExecutor.class);

    /**
     * Sorts the list of components by their components capacity end writes it to
     * the file.
     * 
     * @param Array list of the components to sort,
     * @see Component
     */
    public static void componentSort(ArrayList<Component> list) {
        Collections.sort(list);
        for (Component c : list) {
            TextInputOutput.writeToFileTask(c);
        }
    }

    /**
     * Searches for the unique word from the first sentence end writes it to the
     * file.
     * @param Array list of the components to sort,
     */
    public static void uniqueWordFromFirstSentence(Component component) {

        try {
            Component firstSentence = getComponentsFromLevel(component, 1).get(0);
            ArrayList<Component> listOfLeafs = getComponentsFromLevel(component, 2);
            Map<String, Integer> map = new HashMap<>();
            for (Component c : listOfLeafs) {
                if (map.containsKey(c.getData())) {
                    map.put(c.getData(), map.get(c.getData()) + 1);
                } else {
                    map.put(c.getData(), 1);
                }
            }
            ArrayList<Component> leafsFromFirst = firstSentence.getList();
            for (Component c : leafsFromFirst) {
                if (map.containsKey(c.getData()) && (map.get(c.getData()) == 1)) {
                    TextInputOutput.writeToFileTask(c);
                }
            }
        } catch (ComponentMismatchException e) {
            LOGGER.error("Cannot go in such search component direction " + e);
        }
    }
    
    /**
     * Helps to get the components from the exact level of the composed data.
     * @param Component to get the parts from, and the level of the tree for search
     * @param int level of the tree
     * @return The ArrayList of the components from the given Component list
     * @throws ComponentMismatchException 
     * @see Component
     */
    public static ArrayList<Component> getComponentsFromLevel(Component component, int level)
            throws ComponentMismatchException {
        ArrayList<Component> listOfComponents = new ArrayList<>();
        int depth = 0;
        if (level < 0) throw new ComponentMismatchException();
        if (depth == level) {
            listOfComponents.addAll(component.getList());
        } else {
            for(Component c: component.getList()) {
                listOfComponents.addAll(getComponentsFromLevel(c, level-1));
            }
        }
        return listOfComponents;
    }
}
