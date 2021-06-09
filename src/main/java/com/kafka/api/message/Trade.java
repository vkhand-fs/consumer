package com.kafka.api.message;

import java.io.Serializable;
import java.lang.Double;

public class Trade implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long timestamp;
	private String symbol;
	private Double price;
	private Long size;
	private String status;

	public Trade() {
	}

	public Trade(Long timestamp, String symbol, Double price, Long size, String status) {
		super();
		this.timestamp = timestamp;
		this.symbol = symbol;
		this.price = price;
		this.size = size;
		this.status = status;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
