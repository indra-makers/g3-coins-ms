package com.co.indra.coinmarketcap.coins.model.request;

import javax.validation.constraints.Pattern;

public class CoinHistoryRequest {

	@Pattern(regexp = "[A-Z]{3}")
	private String symbol;

	private Double high;

	private Double low;

	private Double closePrice;

	private Double volume;

	private Double marketCap;

	public CoinHistoryRequest() {

	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Double getHigh() {
		return high;
	}

	public void setHigh(Double high) {
		this.high = high;
	}

	public Double getLow() {
		return low;
	}

	public void setLow(Double low) {
		this.low = low;
	}

	public Double getClosePrice() {
		return closePrice;
	}

	public void setClosePrice(Double closePrice) {
		this.closePrice = closePrice;
	}

	public Double getVolume() {
		return volume;
	}

	public void setVolume(Double volume) {
		this.volume = volume;
	}

	public Double getMarketCap() {
		return marketCap;
	}

	public void setMarketCap(Double marketCap) {
		this.marketCap = marketCap;
	}

}
