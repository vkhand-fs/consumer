package com.kafka.consumer.service;

import java.util.List;

import com.kafka.api.message.Trade;
import com.kafka.consumer.service.response.StatsResponse;

public interface IConsumerService {

	public List<Trade> getTrades();
	public void saveTrade(Trade trade);
	public StatsResponse getTradesStatistics();

}
