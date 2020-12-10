package com.dk.promotion_engine.promotions;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dk.promotion_engine.Order;
import com.dk.promotion_engine.Product;

public class BundlePromotion extends AbstractPromotion {
	private static Logger LOGGER = LoggerFactory.getLogger(BundlePromotion.class);

	@Override
	public Order applyRule(Order order) {
		LOGGER.info("Applying bundle promotion");
		LOGGER.info("Checking for rule C & D for 30");
		calculateBundleItems(order, 30.00, "C", "D");
		
		LOGGER.info("Order total after applying bundle promotion : {}",order.getOrderTotal());
		return applyNextRule(order);
	}

	private void calculateBundleItems(Order order, double bundlePrice, String... productSKUs) {
		if (checkDuplicates(productSKUs)) {
						
			List<String> skus = Arrays.asList(productSKUs);
			List<String> products = order.getProducts().stream().map(Product::getId).collect(Collectors.toList());
			
			while(products.containsAll(skus)) {
				LOGGER.debug("Bulk match found");
				order.setOrderTotal(order.getOrderTotal()+bundlePrice);
				for (String sku : skus) {
					products.remove(sku);
					AtomicInteger index = new AtomicInteger(0);
					order.getProducts().removeIf(product -> product.getId().equalsIgnoreCase(sku) && index.getAndIncrement() < 1);
				}
				
			}
		
			
		} else {
			LOGGER.warn("Invalid Rule, Duplicate in bundle detected");
		}

	}

	private boolean checkDuplicates(String[] productSKUs) {
		Long distinctCount = Stream.of(productSKUs).distinct().count();
		if (distinctCount == productSKUs.length) {
			return true;
		}
		return false;

	}

}
