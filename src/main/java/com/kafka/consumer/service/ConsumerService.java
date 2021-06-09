package com.kafka.consumer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.kafka.api.message.Trade;
import com.kafka.consumer.service.response.BasicTrade;
import com.kafka.consumer.service.response.Statistics;
import com.kafka.consumer.service.response.StatsResponse;
import com.kafka.consumer.service.response.TradeByPrice;
import com.kafka.consumer.service.response.TradeBySize;
import com.kafka.consumer.service.response.TradesByCount;

import java.util.HashMap;

@Service
public class ConsumerService implements IConsumerService {

	private List<Trade> trades;

	@Override
	public List<Trade> getTrades() {
		// Ideally this should get trades from the DB
		return trades;
	}

	@Override
	public void saveTrade(Trade trade) {
		// Ideally this should save in the DB
		if (CollectionUtils.isEmpty(trades)) {
			trades = new ArrayList<>();
		}

		trades.add(trade);
	}

	@Override
	public StatsResponse getTradesStatistics() {
		List<Trade> trades = getTrades();
		if (CollectionUtils.isEmpty(trades))
			return new StatsResponse();
		return buildTradesStatistics(trades);
	}

	private StatsResponse buildTradesStatistics(List<Trade> trades) {
		Map<String, List<Trade>> tradesBySymbolMap = getTradesBySymbolMap(trades);
		return buildResponse(tradesBySymbolMap);
	}

	private StatsResponse buildResponse(Map<String, List<Trade>> tradesBySymbolMap) {
		List<BasicTrade> maxTradeSizes = new ArrayList<>(trades.size());
		List<BasicTrade> avgTradePrices = new ArrayList<>(trades.size());
		List<BasicTrade> tradesCounts = new ArrayList<>(trades.size());

		tradesBySymbolMap.entrySet().stream().forEach(entry -> {
			String symbol = entry.getKey();
			List<Trade> tradesBySymbol = entry.getValue();
			maxTradeSizes.add(getTradeMaxSizeBySymbol(symbol, tradesBySymbol));
			avgTradePrices.add(getAvgPriceBySymbol(symbol, tradesBySymbol));

			// Hardcoded status X for now
			List<Trade> tradesBySymbolAndStatusX = Statistics.getTrades(tradesBySymbol, "X", Statistics.filterByStatus);
			tradesCounts.add(getTradesCountsBySymbolAndByStatus(symbol, tradesBySymbolAndStatusX));
		});

		StatsResponse response = new StatsResponse(maxTradeSizes, avgTradePrices, tradesCounts);
		return response;
	}

	private Map<String, List<Trade>> getTradesBySymbolMap(List<Trade> trades) {
		return trades.stream().collect(Collectors.groupingBy(Trade::getSymbol, Collectors.toList()));
	}

	private BasicTrade getTradeMaxSizeBySymbol(String symbol, List<Trade> tradesBySymbol) {
		Long size = tradesBySymbol.stream().mapToLong(trade -> trade.getSize()).max().getAsLong();
		BasicTrade trade = new TradeBySize(symbol, size);
		return trade;
	}

	private BasicTrade getAvgPriceBySymbol(String symbol, List<Trade> tradesBySymbol) {
		Double avgPrice = tradesBySymbol.stream().mapToDouble(trade -> trade.getPrice()).average().getAsDouble();
		BasicTrade trade = new TradeByPrice(symbol, avgPrice);
		return trade;
	}

	private BasicTrade getTradesCountsBySymbolAndByStatus(String symbol, List<Trade> tradesBySymbolAndStatus) {
		Long count = tradesBySymbolAndStatus.stream().count();
		BasicTrade trade = new TradesByCount(symbol, count);
		return trade;
	}
}
