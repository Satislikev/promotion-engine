package com.dk.promotion_engine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.dk.promotion_engine.promotions.BulkPromotion;
import com.dk.promotion_engine.promotions.BundlePromotion;
import com.dk.promotion_engine.promotions.DefaultPrice;

class PromotionalEngineApplicationTests {

	private Product a = new Product("A", 50.00);
	private Product b = new Product("B", 30.00);
	private Product c = new Product("C", 20.00);
	private Product d = new Product("D", 15.00);

	private IPromotion bulkPromotion;
	private IPromotion bundlePromotion;
	private IPromotion defaultPrice;

	private Order order;
	private List<Product> products;

	@BeforeEach
	public void before() throws Exception {
		bulkPromotion = new BulkPromotion();
		bundlePromotion = new BundlePromotion();
		defaultPrice = new DefaultPrice();
		bulkPromotion.setNextRule(bundlePromotion);
		bundlePromotion.setNextRule(defaultPrice);
		products = new ArrayList<>();

	}

	@Test
	void ValidateProductAndOrder() {

		products.add(a);
		products.add(b);
		products.add(c);
		products.add(d);

		order = new Order(products);

		assertEquals(a.getId(), "A");
		assertEquals(a.getPrice(), 50.00);
		assertNotNull(order.getProducts());
	}

	@Test
	void ValidateScenario1() {

		products.add(a);

		products.add(b);

		products.add(c);

		Order order = new Order(products);
		bulkPromotion.applyRule(order);
		assertEquals(order.getOrderTotal(), 100.00);

	}
	
	@Test
	void ValidateScenario2() {

		products.add(a);
		products.add(a);
		products.add(a);
		products.add(a);
		products.add(a);

		products.add(b);
		products.add(b);
		products.add(b);
		products.add(b);
		products.add(b);

		products.add(c);

		Order order = new Order(products);
		bulkPromotion.applyRule(order);
		assertEquals(order.getOrderTotal(), 370.00);

	}
	
	@Test
	void ValidateScenario3() {

		products.add(a);
		products.add(a);
		products.add(a);


		products.add(b);
		products.add(b);
		products.add(b);
		products.add(b);
		products.add(b);

		products.add(c);
		products.add(d);

		Order order = new Order(products);
		bulkPromotion.applyRule(order);
		assertEquals(order.getOrderTotal(), 280.00);

	}
	
	@Test
	void ValidateScenarioWithZeroProducts() {

		
		Order order = new Order(products);
		bulkPromotion.applyRule(order);
		assertEquals(order.getOrderTotal(), 0.00);

	}

}
