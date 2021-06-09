package com.kafka.consumer.service.response;

import java.util.List;

public class StatsResponse extends ServiceResponse{
	
	private List<BasicTrade> maxTradeSizes;
	private List<BasicTrade> avgTradePrices;
	private List<BasicTrade> tradesCounts;
	
	public StatsResponse(List<BasicTrade> maxTradeSizes, 
			List<BasicTrade> avgTradePrices,
			List<BasicTrade> tradesCounts) {
		super();
		this.maxTradeSizes = maxTradeSizes;
		this.avgTradePrices = avgTradePrices;
		this.tradesCounts = tradesCounts;
	}

	public StatsResponse(){
		super();
	}
	
	public List<BasicTrade> getAvgTradePrices() {
		return avgTradePrices;
	}

	public void setAvgTradePrices(List<BasicTrade> avgTradePrices) {
		this.avgTradePrices = avgTradePrices;
	}

	public List<BasicTrade> getTradesCounts() {
		return tradesCounts;
	}

	public void setTradesCounts(List<BasicTrade> tradesCounts) {
		this.tradesCounts = tradesCounts;
	}

	public List<BasicTrade> getMaxTradeSizes() {
		return maxTradeSizes;
	}

	public void setMaxTradeSizes(List<BasicTrade> maxTradeSizes) {
		this.maxTradeSizes = maxTradeSizes;
	}

	
}
