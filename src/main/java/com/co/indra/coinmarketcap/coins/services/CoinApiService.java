package com.co.indra.coinmarketcap.coins.services;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.co.indra.coinmarketcap.coins.coinApi.CoinExternalApi;
import com.co.indra.coinmarketcap.coins.model.responses.coinApi.CoinApi;
import com.co.indra.coinmarketcap.coins.model.responses.coinApi.CoinApiResponse;

@Service
public class CoinApiService {
	@Autowired
	private CoinExternalApi coinApiResponse;
	public CoinApiResponse getCoinBySymbol(String symbol) {
		CoinApi algo = new CoinApi();
		CoinApiResponse coin = coinApiResponse.getPostsPlainJSON();
		coin.getData().forEach((coinApi) -> {
			if (coinApi.getSymbol().equals(symbol)) {
				algo.setId(coinApi.getId());
				algo.setRank(coinApi.getRank());
				algo.setSymbol(coinApi.getSymbol());
				algo.setName(coinApi.getName());
				algo.setSupply(coinApi.getSupply());
				algo.setMaxSupply(coinApi.getMaxSupply());
				algo.setMarketCapUsd(coinApi.getMarketCapUsd());
				algo.setVolumeUsd24Hr(coinApi.getVolumeUsd24Hr());
				algo.setPriceUsd(coinApi.getPriceUsd());
				algo.setChangePercent24Hr(coinApi.getChangePercent24Hr());
				algo.setVwap24Hr(coinApi.getVwap24Hr());
				algo.setExplorer(coinApi.getExplorer());
			}
		});
		return new CoinApiResponse(algo, coin.getTimestamp());
	}
}
