package com.kafka.consumer.service.response;

public class TradesByCount extends BasicTrade {

	private Long count;

	public TradesByCount(String symbol, Long count){
		super(symbol);
		this.count =  count;
	}
	
	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}
}
