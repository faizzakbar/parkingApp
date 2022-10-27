package com.lawencon.parking;

public enum PriceList {
	CAR("Mobil",5000), BIKE("Motor",1000);
	
	public String type; 
	public int price;
	
	PriceList(String type, int price){
		this.type = type ;
		this.price = price;
	}
}
