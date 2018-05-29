package com.epam.task.two.text.runner;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.epam.task.two.text.entity.Component;
import com.epam.task.two.text.entity.TextComponent;
import com.epam.task.two.text.entity.TextType;

public class Runner {

	private static final Logger logger = Logger.getLogger(Runner.class);
	
	public static void main(String[] args) {

		PropertyConfigurator.configure("resources/log4j.properties");
		logger.debug("Logger had been started");
		
		
		//System.out.println(readFromFile());
		
		Component component = new TextComponent(readFromFile(), TextType.TEXT);
		System.out.println(component);

	}
	
	public static String readFromFile(){
		StringBuilder stringBuilder = new StringBuilder();
		try(Scanner scanner = new Scanner(new FileReader("./resources/Text2Read.txt"))){
			while(scanner.hasNext()) {
				stringBuilder.append(scanner.nextLine());
				stringBuilder.append("\n");
			}
		} catch (IOException e) {
			System.out.println("wrong");
		}
		return stringBuilder.toString();
	}
}
