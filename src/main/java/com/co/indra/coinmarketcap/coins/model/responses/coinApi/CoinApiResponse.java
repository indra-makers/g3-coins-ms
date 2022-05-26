package com.co.indra.coinmarketcap.coins.model.responses.coinApi;

public class CoinApiResponse {

	private CoinApiList data;

	public CoinApiResponse() {

	}

	public CoinApiResponse(CoinApiList data) {
		super();
		this.data = data;
	}

	public CoinApiList getData() {
		return data;
	}

	public void setData(CoinApiList data) {
		this.data = data;
	}

}
