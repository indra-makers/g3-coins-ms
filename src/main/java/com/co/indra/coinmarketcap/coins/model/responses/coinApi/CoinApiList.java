package com.co.indra.coinmarketcap.coins.model.responses.coinApi;



public class CoinApiList {

	private String symbol;

	private String name;

	public CoinApiList() {

	}

	public CoinApiList(String symbol, String name) {
		super();
		this.symbol = symbol;
		this.name = name;

	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
