package com.kafka.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kafka.api.message.Trade;
import com.kafka.consumer.service.IConsumerService;
import com.kafka.consumer.service.response.StatsResponse;
import com.kafka.consumer.service.response.TradesResponse;
import com.kafka.consumer.util.ConsumerConstants;

@RestController
@RequestMapping(value = "/fixed-income")
public class ConsumerController {

	@Autowired
	private IConsumerService service;

	@GetMapping(value = ConsumerConstants.GET_TRADES)
	public TradesResponse getTrades() {
		return new TradesResponse(service.getTrades());
	}

	@KafkaListener(groupId = "trades-1", topics = "fixedincome.trades.data", containerFactory = "userKafkaListenerContainerFactory")
	public void saveTradesDataFromTopic(Trade data) {
		service.saveTrade(data);
	}

	@GetMapping(value = ConsumerConstants.GET_TRADES_STATS)
	public StatsResponse getTradesStatistics() {
		return service.getTradesStatistics();
	}
}
