package com.dk.promotion_engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dk.promotion_engine.promotions.BulkPromotion;
import com.dk.promotion_engine.promotions.BundlePromotion;
import com.dk.promotion_engine.promotions.DefaultPrice;

/**
 * Console Application
 * 
 * @author Kamyar
 *
 */
@SpringBootApplication
public class PromotionEngineApplication implements CommandLineRunner {

	private IPromotion bulkPromotion;
	private IPromotion bundlePromotion;
	private IPromotion defaultPrice;

	private Product a = new Product("A", 50.00);
	private Product b = new Product("B", 30.00);
	private Product c = new Product("C", 20.00);
	private Product d = new Product("D", 15.00);

	private Order order;
	private List<Product> products;
	Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		SpringApplication.run(PromotionEngineApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		System.out.println("Welcome to promotion engine");

		System.out.println("Quantity of product A :");
		getInput();
		int qA = sc.nextInt();

		System.out.println("Quantity of product B :");
		getInput();
		int qB = sc.nextInt();

		System.out.println("Quantity of product C :");
		getInput();
		int qC = sc.nextInt();

		System.out.println("Quantity of product D :");
		getInput();
		int qD = sc.nextInt();

		validateInput(qA, qB, qC, qD);

		System.out.println("Thank you! Calculating your total");

		bulkPromotion = new BulkPromotion();
		bundlePromotion = new BundlePromotion();
		defaultPrice = new DefaultPrice();
		bulkPromotion.setNextRule(bundlePromotion);
		bundlePromotion.setNextRule(defaultPrice);
		products = new ArrayList<>();

		for (int i = 0; i < qA; i++) {
			products.add(a);
		}
		for (int i = 0; i < qB; i++) {
			products.add(b);
		}
		for (int i = 0; i < qC; i++) {
			products.add(c);
		}
		for (int i = 0; i < qD; i++) {
			products.add(d);
		}

		order = new Order(products);
		System.out.println("Your order total is : " + bulkPromotion.applyRule(order).getOrderTotal());

	}

	private void getInput() {
		while (!sc.hasNextInt()) {
			System.out.println("Please enter valid number");
			sc.next();
		}

	}

	private void validateInput(int... ints) {
		for (int i : ints) {
			if (i < 0) {
				System.out.println("WARNING : Please note negative quantity is considered as 0");
			}
			if (i > 5000) {
				System.out.println("Quanity is outside of supported range");
				System.exit(1);
			}
		}
	}

}
