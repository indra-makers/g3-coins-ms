package com.co.indra.coinmarketcap.coins.model.responses.coinApi;

public class CoinsApiResponse {
	
	private CoinApi data;
	private Double timestamp;
	public CoinsApiResponse(CoinApi data, Double timestamp) {
		super();
		this.data = data;
		this.timestamp = timestamp;
	}
	public CoinApi getData() {
		return data;
	}
	public void setData(CoinApi data) {
		this.data = data;
	}
	public Double getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Double timestamp) {
		this.timestamp = timestamp;
	}
	
	

}
