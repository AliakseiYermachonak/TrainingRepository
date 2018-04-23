package com.epam.task1.taxistation.model;

public class Cab implements Comparable<Cab> {
	
	//unique number for each cab
	private static int counter = 0;
	
	private int speed;
	private int fuelConsumption;
	private int price;
	private int identifier;
	
	public Cab() {
		this.speed = 0;
		this.fuelConsumption = 0;
		this.price = 0;
		counter++;
		this.identifier = counter;
	}
	
	public Cab(int speed, int fuelConsumption, int price) {
		this.speed = speed;
		this.fuelConsumption = fuelConsumption;
		this.price = price;
		counter++;
		this.identifier = counter;
	}
	
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getFuelConsumption() {
		return fuelConsumption;
	}

	public void setFuelConsumption(int fuelConsumption) {
		this.fuelConsumption = fuelConsumption;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	@Override
	public int compareTo(Cab o) {
		if (this.fuelConsumption < o.fuelConsumption) {
			return -1;
		} else if (this.fuelConsumption > o.fuelConsumption) {
			return 1;
		}
		else {
			return 0;
		}
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder("Taxi №" + identifier);
		if (speed>0) {
			s.append(", has speed " + speed);
		} else {
			s.append(", speed is unknown");
		}
		
		if (fuelConsumption>0) {
			s.append(" and fuel consumption " + fuelConsumption);
		} else {
			s.append(", fuel consumption is unknown,");
		}
		
		if (price>0) {
			s.append(", costs " + price);
		} else {
			s.append(", price is also unknown.");
		}
		return s.toString();
	}

}
