package com.kafka.consumer.service.response;

import java.util.List;

import com.kafka.api.message.Trade;

public class TradesResponse {
	
	private List<Trade> trades;

	public TradesResponse(List<Trade> trades) {
		super();
		this.trades = trades;
	}

	public List<Trade> getTrades() {
		return trades;
	}

	public void setTrades(List<Trade> trades) {
		this.trades = trades;
	} 
}
