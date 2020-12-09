package com.dk.promotional_engine;

public class Product {

	public Product(String id, double price) {
		super();
		this.id = id;
		this.price = price;
	}

	private String id;

	private double price;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", price=" + price + "]";
	}

}
