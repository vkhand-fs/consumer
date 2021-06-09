package com.kafka.consumer.service.response;

public class BasicTrade {

	private String symbol;

	public BasicTrade() {
	}

	public BasicTrade(String symbol) {
		super();
		this.symbol = symbol;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

}
