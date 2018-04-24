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
	
	public void addCab(Cab cab) {
		cabList.add(cab);
	}
	
	public int getSize() {
		return cabList.size();
	}
}
