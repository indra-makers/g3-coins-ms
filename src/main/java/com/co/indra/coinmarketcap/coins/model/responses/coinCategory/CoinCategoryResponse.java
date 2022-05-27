package com.co.indra.coinmarketcap.coins.model.responses.coinCategory;

import java.util.List;

import org.springframework.data.domain.Page;

public class CoinCategoryResponse {

	private List<CoinCategoryList> coinList;
	private Page<CoinCategoryList> coinCategoryPageable;

	public CoinCategoryResponse(List<CoinCategoryList> coinList) {
		super();
		this.coinList = coinList;

	}

	public CoinCategoryResponse(Page<CoinCategoryList> coinCategoryPageable) {
		super();
		this.coinCategoryPageable = coinCategoryPageable;
	}

	public List<CoinCategoryList> getCoinList() {
		return coinList;
	}

	public void setCoinList(List<CoinCategoryList> coinList) {
		this.coinList = coinList;
	}

	public Page<CoinCategoryList> getCoinCategoryPageable() {
		return coinCategoryPageable;
	}

	public void setCoinCategoryPageable(Page<CoinCategoryList> coinCategoryPageable) {
		this.coinCategoryPageable = coinCategoryPageable;
	}

<<<<<<< HEAD

}
=======
}
>>>>>>> 14fdf7ca405691e1874c746ae617226d3cef80a5
