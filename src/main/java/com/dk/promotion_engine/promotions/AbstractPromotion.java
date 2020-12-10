package com.dk.promotion_engine.promotions;

import com.dk.promotion_engine.IPromotion;
import com.dk.promotion_engine.Order;

public abstract class AbstractPromotion implements IPromotion {
	
	private IPromotion next;

	@Override
	public void setNextRule(IPromotion next) {
		this.next = next;
	}
	
	
	public Order applyNextRule(Order order) {
		if (next != null) {
			return next.applyRule(order);
		}
		
		return order;
	}

}
