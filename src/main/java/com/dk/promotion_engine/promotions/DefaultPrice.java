package com.dk.promotion_engine.promotions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dk.promotion_engine.Order;
import com.dk.promotion_engine.Product;

public class DefaultPrice extends AbstractPromotion {

	private static Logger LOGGER = LoggerFactory.getLogger(DefaultPrice.class);
	
	@Override
	public Order applyRule(Order order) {
		LOGGER.info("Applying default price");
		for (Product product : order.getProducts()) {
			order.setOrderTotal(order.getOrderTotal()+product.getPrice());
		}
		LOGGER.info("Order total after applying default price : {}",order.getOrderTotal());
		return order;

	}
	
	

}
