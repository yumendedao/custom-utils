package com.yumendedao.example.FactoryAndPaging;

public class Product implements IProduct {
	
	int id;
	String name;
	int price;
	
	public Product(int id, String name, int price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public String getName(){
		return name;
	}
	
	public int getPrice(){
		return price;
	}

}
