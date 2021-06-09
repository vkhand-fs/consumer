package com.kafka.consumer.service.response;

public class TradeBySize extends BasicTrade {

	private Long size;

	public TradeBySize(String symbol, Long size){
		super(symbol);
		this.size =  size;
	}
	
	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}
}
