package com.co.indra.coinmarketcap.coins.model.responses.coinApi;

import java.util.List;

public class CoinApiResponse {

	private List<CoinApi> data;
	private CoinApi asset;
	private Double timestamp;

	public CoinApiResponse() {

	}

	public CoinApiResponse(List<CoinApi> data, Double timestamp) {
		super();
		this.data = data;
		this.timestamp = timestamp;
	}

	public CoinApiResponse(CoinApi asset, Double timestamp) {
		super();
		this.asset = asset;
		this.timestamp = timestamp;
	}

	public List<CoinApi> getData() {
		return data;
	}

	public void setData(List<CoinApi> data) {
		this.data = data;
	}

	public Double getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Double timestamp) {
		this.timestamp = timestamp;
	}

	public CoinApi getasset() {
		return asset;
	}

	public void setasset(CoinApi asset) {
		this.asset = asset;
	}

}
