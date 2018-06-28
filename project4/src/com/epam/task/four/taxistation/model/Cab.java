package com.epam.task.four.taxistation.model;

/**
 * This class is used to describe some specific cab
 * and keeps its main attributes
 * @author Alexey Yermachyonok
 * @version 1.0
 */
public class Cab implements Comparable<Cab> {
	
	/**
	 * Counter for a unique identifier for each cab
	 */
	private static int counter = 0;
	
	private int speed;
	private int fuelConsumption;
	private int price;
	private int identifier;
	
	/**
	 * Creates new object with identifier (zero) parameters
	 * @param identifier
	 */
	public Cab(int identifier) {
		this.identifier = identifier;
	}
	
	/**
	 * Creates new object with default (zero) parameters
	 */
	public Cab() {
		this.speed = 0;
		this.fuelConsumption = 0;
		this.price = 0;
		counter++;
		this.identifier = counter;
	}
	
	/**Creates new object with given parameters
	 * @param speed
	 * @param fuelConsumption
	 * @param price
	 */
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
	
	/**
	 * Comparison of the cabs according to their fuel consumption
	 */
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
		StringBuilder s = new StringBuilder(this.getClass().getSimpleName());
		s.append(" #");
		s.append(identifier);
		s.append(", has speed ");
		s.append(speed);
		s.append(" and fuel consumption ");
		s.append(fuelConsumption);
		s.append(", costs ");
		s.append(price);
		
		return s.toString();
	}

	@Override
	public int hashCode() {
		final int primefuelConumption = 7;
		final int primePrice = 31;
		final int primeSpeed = 57;
		int result = 1;
		result = primefuelConumption * fuelConsumption;
		result = result + price * primePrice;
		result = result + primeSpeed;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cab other = (Cab) obj;
		if (fuelConsumption != other.fuelConsumption)
			return false;
		if (price != other.price)
			return false;
		if (speed != other.speed)
			return false;
		return true;
	}

}
