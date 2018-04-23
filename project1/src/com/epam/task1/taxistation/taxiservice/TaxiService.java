package com.epam.task1.taxistation.taxiservice;

import java.util.Collections;
import java.util.Scanner;

import com.epam.task1.taxistation.exception.TaxiNotFoundException;
import com.epam.task1.taxistation.model.Cab;
import com.epam.task1.taxistation.model.TaxiStation;

public class TaxiService {
	
	private TaxiStation taxiStation;

	public TaxiService(TaxiStation taxiStation) {
		this.taxiStation = taxiStation;
	}

	//generates a list of random cabs with random parameters
	public void generateCabList() {
		if (!taxiStation.getCabList().isEmpty()) {
			taxiStation.getCabList().clear();
		}
		int n = (int) (Math.random()*6 + 5);
		for (int i = 0; i < n; i++) {
			int price = ((int) (Math.random()*600 + 500)) * (i+1);
			int speed = ((int) (Math.random()*16 + 15)) * (i+1);
			int fuel = ((int) (Math.random()*16 + 5));
			taxiStation.addCab(speed, fuel, price);
		}
		showCabList();
	}
	
	//shows each cab from the list
	public void showCabList() {
		if (taxiStation.getCabList().isEmpty()) {
			System.out.println("Note: your taxi station is empty");
			return;
		}
		for (Cab cab: taxiStation.getCabList()) {
			System.out.println(cab);
		}
	}
	
	//sorts cabs according to their fuel consumption
	public void sortCabs(){
		if (taxiStation.getCabList().isEmpty()) {
			System.out.println("Note: your taxi station is empty");
			return;
		}
		Collections.sort(taxiStation.getCabList());
		showCabList();
	}
	
	//shows the total taxi station price
	public int showPrice() {
		if (taxiStation.getCabList().isEmpty()) {
			System.out.println("Note: your taxi station is empty");
		}
		int sum = 0;
		for (Cab cab: taxiStation.getCabList()) {
			sum += cab.getPrice();
		}
		System.out.println("Total taxi station price: " + sum);
		return sum;
	}
	
	//looks for a cab with speed parameter from the received delta
	public Cab showCabWithSpeed() {
		if (taxiStation.getCabList().isEmpty()) {
			System.out.println("Note: your taxi station is empty");
		}

		int a = -1, b = -1;
		
		Scanner scanner = new Scanner(System.in);
		try {
			System.out.println("Bottom line of taxi speed is ");
			a = Integer.parseInt(scanner.next());
			System.out.println("Upper bound of taxi speed is ");
			b = Integer.parseInt(scanner.next());
			if (a>b) {
				a+=b;
				b = a - b;
				a-=b;
			}
			for (Cab cab: taxiStation.getCabList()) {
				if (cab.getSpeed()>=a && cab.getSpeed()<=b) {
					System.out.println("You are looking for a \n" + cab);
					return cab;
				}
			}
		} catch (NumberFormatException e) {
			System.out.println("Please, enter a number");
		}
		throw new TaxiNotFoundException();	
	}
}
