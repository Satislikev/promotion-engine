package com.dk.promotion_engine.promotions;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dk.promotion_engine.Order;
import com.dk.promotion_engine.Product;

/** Promotion will cover buy X and Y for Z amount.
 * @author Kamyar Aflaki 
 *
 */
public class BundlePromotion extends AbstractPromotion {
	private static Logger LOGGER = LoggerFactory.getLogger(BundlePromotion.class);

	@Override
	public Order applyRule(Order order) {
		// This rule assumes that that quantity of promoted SKUs are same, In other word
		// we will not have 2C and D for X price.
		LOGGER.info("Applying bundle promotion");
		LOGGER.info("Checking for rule C & D for 30");
		calculateBundleItems(order, 30.00, "C", "D");

		LOGGER.info("Order total after applying bundle promotion : {}", order.getOrderTotal());
		return applyNextRule(order);
	}

	private void calculateBundleItems(Order order, double bundlePrice, String... productSKUs) {
		if (checkDuplicates(productSKUs)) {

			// Saving Promoted SKUs in list
			Set<String> promotedSkus = new HashSet<>(Arrays.asList(productSKUs));

			// Grouping and counting products that are part of the SKUs
			Map<Product, Long> groupedItems = order.getProducts().stream()
					.filter(product -> promotedSkus.contains(product.getId()))
					.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

			// If size of the grouped and counted map is lower than length of promoted SKU
			// Set,
			// it means that not all product are available in map hence rule doesn't apply.
			if (groupedItems.size() == productSKUs.length) {

				// Finding lowest number.
				Optional<Long> lowest = groupedItems.entrySet().stream()
						.sorted(Comparator.comparingLong(Map.Entry::getValue)).findFirst().map(Map.Entry::getValue);

				// A bit dangerous to multiply double with long
				order.setOrderTotal(order.getOrderTotal() + (lowest.get() * bundlePrice));

				// Removing products that have promotion applied on them
				for (String sku : promotedSkus) {
					AtomicLong index = new AtomicLong(0);
					order.getProducts().removeIf(
							product -> product.getId().equalsIgnoreCase(sku) && index.getAndIncrement() < lowest.get());
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
