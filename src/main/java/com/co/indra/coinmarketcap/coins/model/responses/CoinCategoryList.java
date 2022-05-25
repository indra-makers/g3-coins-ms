package com.co.indra.coinmarketcap.coins.model.responses;

public class CoinCategoryList {
	
	private String nameCoin;
	private String symbol;
	public CoinCategoryList(String nameCoin, String symbol) {
		super();
		this.nameCoin = nameCoin;
		this.symbol = symbol;
	}
	public String getNameCoin() {
		return nameCoin;
	}
	public void setNameCoin(String nameCoin) {
		this.nameCoin = nameCoin;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	

}
