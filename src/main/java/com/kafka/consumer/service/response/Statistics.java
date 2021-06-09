package com.kafka.consumer.service.response;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

import com.kafka.api.message.Trade;

public class Statistics {
	
	public static final BiPredicate<Trade, String> filterBySymbol = (trade, symbol) -> trade.getSymbol().equalsIgnoreCase(symbol);
	public static final BiPredicate<Trade, String> filterByStatus = (trade, status) -> trade.getStatus().equalsIgnoreCase(status);
	public static final BiPredicate<Trade, Long> filterBySize = (trade, size) -> trade.getSize().equals(size);
	public static final BiPredicate<Trade, Double> filterByPrice = (trade, price) -> trade.getPrice().equals(price);
	
	
	public static List<Trade> getTrades(List<Trade> trades, String criteria, BiPredicate<Trade, String> biPredicate) {
		return trades.stream().filter(trade -> biPredicate.test(trade, criteria)).collect(Collectors.toList());
	}
	
	public static List<Trade> getTrades(List<Trade> trades, Long criteria, BiPredicate<Trade, Long> biPredicate) {
		return trades.stream().filter(trade -> biPredicate.test(trade, criteria)).collect(Collectors.toList());
	}
	
	public static List<Trade> getTrades(List<Trade> trades, Double criteria, BiPredicate<Trade, Double> biPredicate) {
		return trades.stream().filter(trade -> biPredicate.test(trade, criteria)).collect(Collectors.toList());
	}
	
}
