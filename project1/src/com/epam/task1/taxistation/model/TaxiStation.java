package com.epam.task1.taxistation.model;

import java.util.ArrayList;
import java.util.List;

public class TaxiStation {

	private List<Cab> cabList = new ArrayList<Cab>();
	
	public List<Cab> getCabList() {
		return cabList;
	}

	public void setCabList(List<Cab> cabList) {
		this.cabList = cabList;
	}
	
	public void addCab(int speed, int fuelConsumption, int price) {
		cabList.add(new Cab(speed, fuelConsumption, price));
	}
	
	public int getSize() {
		return cabList.size();
	}
}
