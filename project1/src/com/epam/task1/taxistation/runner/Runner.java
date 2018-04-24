package com.epam.task1.taxistation.runner;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.epam.task1.taxistation.exception.TaxiNotFoundException;
import com.epam.task1.taxistation.model.TaxiStation;
import com.epam.task1.taxistation.taxiservice.TaxiService;

public class Runner {

	public static void main(String[] args) {
		runTaxiStationMenu(new TaxiStation());
	}
	
	//shows menu on console
	public static void runTaxiStationMenu(TaxiStation taxiStation) {
		TaxiService taxiService = new TaxiService(taxiStation);
		while (true) {
			showMenuLines();
			switch (doCommand()) {
				case 1: { 
					taxiService.generateCabList();
					break;
				}
				case 2: { 
					taxiService.showPrice();
					break;
				}
				case 3: { 
					taxiService.sortCabs();
					break;
				}
				case 4: {
					try {
						taxiService.showCabWithSpeed();
						break;
					} catch (TaxiNotFoundException e) {
						System.out.println("There are no a suchlike taxi");
						e.printStackTrace();
						continue;
					}
				}
				case 0: {
					System.out.println("You are leaving the program, \nGoodbye!");
					return;
				}
				default: {
					System.out.println("Please, try again.");
					break;
				}
			}
		}
	}
	
	//printing the menu on console
	public static void showMenuLines() {
		System.out.println("Welcom to our taxi station, \nselect one of the next options:" );
		System.out.println("1 -- Generate a set of random taxis");
		System.out.println("2 -- Count the total price");
		System.out.println("3 -- Sort the taxis according to the fuel consumption");
		System.out.println("4 -- Get the cab with speed from the range");
		System.out.println("0 -- Exit");
	}
	
	//Reads the number of selected option
	public static int doCommand() {
		int input = -1;
		try {
			Scanner scanner = new Scanner(System.in);
			input = scanner.nextInt();
			if (input == 0) {
				scanner.close();
			}
		} catch(InputMismatchException e) {
			System.out.println("Input is not a number.");
			e.printStackTrace();
		}
		return input;
	}

}
