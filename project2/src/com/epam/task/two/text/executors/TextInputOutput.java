package com.epam.task.two.text.executors;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.epam.task.two.text.entity.Component;
import com.epam.task.two.text.entity.TextType;

/**
 * Class container of the static methods
 * for reading and writing text from/to file. 
 * @author Alexey Yermachyonok
 * @version 1.0
 */

public class TextInputOutput {
	
	private static final Logger logger = Logger.getLogger(TextInputOutput.class);
	static {
		PropertyConfigurator.configure("resources/log4j.properties");
	}
	
	/**
	 * Reads text from the predefined file.
	 * @return String text.
	 */
	public static String readFromFile(){
		StringBuilder stringBuilder = new StringBuilder();
		logger.debug("start reading file");
		try(Scanner scanner = new Scanner(new FileReader("./resources/Text2Read.txt"))){
			while(scanner.hasNext()) {
				stringBuilder.append(scanner.nextLine());
				stringBuilder.append("\n");
			}
		} catch (IOException e) {
			logger.error(e);
		}
		logger.debug("file successfully read");
		return stringBuilder.toString();
	}
	
	/**
	 * Writes text to the predefined file.
	 * @param Component to get the text from.
	 * @see Component
	 */
	public static void writeToFileText(Component component){
		String fileName = "./resources/File2Write.txt";	
		try(FileWriter fileWriter = new FileWriter(fileName, false)){
            fileWriter.write(component.toString());
            fileWriter.flush();
        }
        catch(IOException e){
            logger.error(e);
        } 
	}
	
	/**
	 * Writes text of the tasks into the predefined files.
	 * @param Component to get the text from.
	 * @see Component
	 */
	public static void writeToFileTask(Component component){
		String fileName;
		if (component.getTextType() == TextType.SENTENCE) {
			fileName = "./resources/File4Task1.txt";
		} else {
			fileName = "./resources/File4Task2.txt";
		}
		
		try(FileWriter fileWriter = new FileWriter(fileName, true)){
            fileWriter.write(component.toString() + "\n");
            fileWriter.flush();
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        } 
	}
}
