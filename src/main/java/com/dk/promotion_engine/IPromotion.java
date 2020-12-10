package com.dk.promotion_engine;

/** Interface for promotion rules based on chain of responsibility
 * @author Kamyar
 *
 */
public interface IPromotion {

	void setNextRule(IPromotion next);

	Order applyRule(Order order);
}
