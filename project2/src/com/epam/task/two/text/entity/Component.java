package com.epam.task.two.text.entity;

import java.util.ArrayList;

/**
 * Basic component interface for realization
 * of Composition pattern.
 * @author Alexey Yermachyonok
 * @version 1.0
 */
public interface Component extends Comparable<Component>{
    
    /**
     * Gets list of containing components.
     * @return ArrayList of the Components
     */
    ArrayList<Component> getList();
    
    /**
     * Adds component to the list of components.
     * @param Component to add
     */
    void add(Component component);
    
    /**
     * Adds list of components to the list of components.
     * @param ArrayList of the Components
     */
    void addAll(ArrayList<Component> components);
    
    /**
     * Gets the text of the list of the components.
     * @return String text.
     */
    String getData();
    
    /**
     * Writes the text to the component.
     * @param String text.
     */
    void setData(String data);
    
    /**
     * Gets the number of element in the list of components.
     * @return int length.
     */
    int getLength();
    
}