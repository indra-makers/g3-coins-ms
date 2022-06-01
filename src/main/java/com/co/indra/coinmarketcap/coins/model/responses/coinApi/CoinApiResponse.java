package com.co.indra.coinmarketcap.coins.model.responses.coinApi;

import java.util.List;

public class CoinApiResponse {

	private List<CoinApi> data;
	private CoinApi assets;
	private Double timestamp;

	public CoinApiResponse() {

	}

	public CoinApiResponse(List<CoinApi> data, Double timestamp) {
		super();
		this.data = data;
		this.timestamp = timestamp;
	}

	public CoinApiResponse(CoinApi assets, Double timestamp) {
		super();
		this.assets = assets;
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

	public CoinApi getAssets() {
		return assets;
	}

	public void setAssets(CoinApi assets) {
		this.assets = assets;
	}

}
