package com.dk.promotion_engine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

class PromotionalEngineApplicationTests {

	@Test
	void ValidateProductAndOrder() {
		Product a = new Product("A",50.00);
		Product b = new Product("B",30.00);
		Product c = new Product("C",20.00);
		Product d = new Product("D",15.00);
		
		List<Product> products = new ArrayList<>();
		products.add(a);
		products.add(b);
		products.add(c);
		products.add(d);
		
		Order order = new Order(products);
		
		assertEquals(a.getId(), "A");
		assertEquals(a.getPrice(), 50.00);
		assertNotNull(order.getProducts());
	}

}
