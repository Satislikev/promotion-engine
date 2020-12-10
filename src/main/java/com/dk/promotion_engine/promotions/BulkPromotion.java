package com.dk.promotion_engine.promotions;

import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dk.promotion_engine.Order;

public class BulkPromotion extends AbstractPromotion {

	private static Logger LOGGER = LoggerFactory.getLogger(BulkPromotion.class);
	
	@Override
	public Order applyRule(Order order) {
		LOGGER.info("Applying bulk promotion");
		
		LOGGER.info("Checking for rule 3A = 130");
		calculateBulkItems(order, "A", 3L, 130.00);
		
		LOGGER.info("Checking for rule 2B = 45");
		calculateBulkItems(order, "B", 2L, 45.00);
		
		LOGGER.info("Order total after applying bulk promotion : {}",order.getOrderTotal());
		return applyNextRule(order);
	}
	
	private Order calculateBulkItems(Order order,String productSKU,Long bulkQuantity, double bulkPrice) {
		Long quanity = order.getProducts().stream().filter(product -> product.getId().equalsIgnoreCase(productSKU)).count();
		if(quanity >= bulkQuantity) {
			bulkPrice = (quanity/bulkQuantity) * bulkPrice;
			order.setOrderTotal(order.getOrderTotal() + bulkPrice);
			
			AtomicInteger index = new AtomicInteger(0);
			long bulkQuantityMultiplier = (quanity/bulkQuantity) * bulkQuantity;
			
			order.getProducts().removeIf(product -> product.getId().equalsIgnoreCase(productSKU) && index.getAndIncrement() < bulkQuantityMultiplier);
			
		}
		return order;

	}

}
