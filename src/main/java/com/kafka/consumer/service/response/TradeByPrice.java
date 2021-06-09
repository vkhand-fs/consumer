package com.kafka.consumer.service.response;

public class TradeByPrice extends BasicTrade {

	private Double price;

	public TradeByPrice(String symbol, Double price){
		super(symbol);
		this.price =  price;
	}
	
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}
