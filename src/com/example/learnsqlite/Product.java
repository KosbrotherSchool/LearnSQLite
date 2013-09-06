package com.example.learnsqlite;

public class Product {
	private int id;
	private String name;

	public Product() {
		
	}
	
	public Product(int id, String productname) {
		this.id = id;
		this.name = productname;
	}
	
	public Product(String productname) {
		this.name = productname;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public int getID() {
		return this.id;
	}
	
	public void setProductName(String productname) {
		this.name = productname;
	}
	
	public String getProductName() {
		return this.name;
	}
	
}