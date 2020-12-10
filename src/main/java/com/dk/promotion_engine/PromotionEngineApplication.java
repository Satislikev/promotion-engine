package com.dk.promotion_engine;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dk.promotion_engine.promotions.BulkPromotion;
import com.dk.promotion_engine.promotions.BundlePromotion;


@SpringBootApplication
public class PromotionEngineApplication implements CommandLineRunner {

	private static Logger LOGGER = LoggerFactory.getLogger(PromotionEngineApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PromotionEngineApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Product A = new Product("A",50.00);
		Product B = new Product("B",30.00);
		Product C = new Product("C",20.00);
		Product D = new Product("D",15.00);
		
		List<Product> products = new ArrayList<>();
		products.add(A);
		products.add(A);
		products.add(A);
		products.add(A);
		products.add(A);
		products.add(A);
		products.add(A);
		products.add(B);
		products.add(B);
		products.add(C);
		products.add(C);
		products.add(D);
		products.add(D);
		products.add(D);
		products.add(D);
		
		Order order = new Order(products);
		
		IPromotion bulkPromotion = new BulkPromotion();
		IPromotion bundIPromotion = new BundlePromotion();
		
		bulkPromotion.setNextRule(bundIPromotion);
		
		Order discountedOrder = bulkPromotion.applyRule(order);
		
		LOGGER.info(discountedOrder.toString());

	}

}
