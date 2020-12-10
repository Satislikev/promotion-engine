package com.dk.promotion_engine;

import java.util.List;

public class Order {
	
	public Order(List<Product> products) {
		super();
		this.products = products;
	}

	private List<Product> products;
	
	private double orderTotal;

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public double getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(double orderTotal) {
		this.orderTotal = orderTotal;
	}

	@Override
	public String toString() {
		return "Order [products=" + products + ", orderTotal=" + orderTotal + "]";
	}
	
	
}
