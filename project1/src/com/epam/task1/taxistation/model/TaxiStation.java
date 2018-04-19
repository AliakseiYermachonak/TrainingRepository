package com.epam.task1.taxistation.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaxiStation {

	private List<Cab> cabList = new ArrayList<Cab>();
	
	//������������� �������� ������ �� 5-10 ���� �� ���������� ����������� � ������� �� �� �����
	public void generateCabs() {
		if (!cabList.isEmpty()) cabList.clear();
		int n = (int) Math.random()*6 + 5;
		for (int i = 0; i < n; i++) {
			int price = ((int) (Math.random()*600 + 500)) * (i+1);
			int speed = ((int) (Math.random()*16 + 15)) * (i+1);
			int fuel = ((int) (Math.random()*16 + 5));
			cabList.add(new Cab(speed, fuel, price));
		}
		showCabs();
	}
	
	//����� ������ ����� �� �����
	public void showCabs() {
		if (cabList.isEmpty()) {
			System.out.println("����������: ��� ������ ���� ����");
			return;
		}
		for (Cab cab: cabList) {
			System.out.println(cab);
		}
	}
	
	//������������� ���� �� ������� �������
	public void sortCabs(){
		if (cabList.isEmpty()) {
			System.out.println("����������: ��� ������ ���� ����");
			return;
		}
		Collections.sort(cabList);
		showCabs();
	}
	
	//������� �� ����� ����� ��������� ����������
	public int showPrice() {
		if (cabList.isEmpty()) {
			System.out.println("����������: ��� ������ ���� ����");
		}
		int sum = 0;
		for (Cab cab: cabList) {
			sum += cab.getPrice();
		}
		System.out.println("��������� ���������: " + sum);
		return sum;
	}
	
	//����� ���� �� ���������, ���������� � �������
	public Cab showCabWithSpeed() {
		if (cabList.isEmpty()) {
			System.out.println("����������: ��� ������ ���� ����");
			return null;
		}
		String input;
		int a = -1, b = -1;
		System.out.println("����������, ������� �������� �� ���� ����� ����� ������");
		InputStreamReader iS = new InputStreamReader(System.in);
		BufferedReader bR = new BufferedReader(iS);
		try {
			input = bR.readLine();
			a = Integer.parseInt(input.split(" ")[0]);
			b = Integer.parseInt(input.split(" ")[1]);
			if (a>b) {
				a+=b;
				b = a - b;
				a-=b;
			}
			for (Cab cab: cabList) {
				if (cab.getSpeed()>=a && cab.getSpeed()<=b) {
					System.out.println("������� ���� ���������� " + cab);
					return cab;
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("�� �� ����� ��� �����");
		} catch (NumberFormatException e) {
			System.out.println("�� �� ����� ��� �����");
		} catch (IOException e) {
			System.out.println("������ ����� ������");
		}
		System.out.println("���� � ������ ����������� �� ���������� � ����������");
		return null;	
	}
	
	public List<Cab> getCabList() {
		return cabList;
	}

	public void setCabList(List<Cab> cabList) {
		this.cabList = cabList;
	}
}
