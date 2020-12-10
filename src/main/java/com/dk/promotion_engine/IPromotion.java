package com.dk.promotion_engine;

public interface IPromotion {

	void setNextRule(IPromotion next);

	Order applyRule(Order order);
}
