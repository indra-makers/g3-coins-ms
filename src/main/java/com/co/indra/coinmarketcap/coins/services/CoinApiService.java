package com.co.indra.coinmarketcap.coins.services;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.co.indra.coinmarketcap.coins.coinApi.CoinExternalApi;
import com.co.indra.coinmarketcap.coins.config.Routes;
import com.co.indra.coinmarketcap.coins.model.responses.coinApi.CoinApi;
import com.co.indra.coinmarketcap.coins.model.responses.coinApi.CoinApiResponse;
import com.co.indra.coinmarketcap.coins.model.responses.coinApi.CoinsApiResponse;

@Service
public class CoinApiService {

	private Map<String, String> mapCoin = new HashMap<>();

	@Autowired
	private CoinExternalApi coinApiResponse;

	/*
	 * public CoinApiResponse getCoinBySymbol(String symbol) { CoinApi algo = new
	 * CoinApi(); CoinApiResponse coin = coinApiResponse.getPostsPlainJSON();
	 * coin.getData().forEach((coinApi) -> { if (coinApi.getSymbol().equals(symbol))
	 * { algo.setId(coinApi.getId()); algo.setRank(coinApi.getRank());
	 * algo.setSymbol(coinApi.getSymbol()); algo.setName(coinApi.getName());
	 * algo.setSupply(coinApi.getSupply());
	 * algo.setMaxSupply(coinApi.getMaxSupply());
	 * algo.setMarketCapUsd(coinApi.getMarketCapUsd());
	 * algo.setVolumeUsd24Hr(coinApi.getVolumeUsd24Hr());
	 * algo.setPriceUsd(coinApi.getPriceUsd());
	 * algo.setChangePercent24Hr(coinApi.getChangePercent24Hr());
	 * algo.setVwap24Hr(coinApi.getVwap24Hr());
	 * algo.setExplorer(coinApi.getExplorer()); } }); return new
	 * CoinApiResponse(algo, coin.getTimestamp()); }
	 */

	@PostConstruct
	public void extractCoinSymbol() {
		 String url = "https://api.coincap.io/v2/assets";
		CoinApiResponse responseUrl = coinApiResponse.getRestTemplate().getForObject(url, CoinApiResponse.class);
		responseUrl.getData().forEach((coinApi) -> {
			mapCoin.put(coinApi.getSymbol(), coinApi.getId());
		});
	}

	public CoinApi getCoinsBySymbol(String symbol) {
		 String url = "https://api.coincap.io/v2/assets/" + mapCoin.get(symbol);
		CoinsApiResponse responseUrl = coinApiResponse.getRestTemplate().getForObject(url, CoinsApiResponse.class);

		return new CoinApi(responseUrl.getData().getId(), responseUrl.getData().getRank(),
				responseUrl.getData().getSymbol(), responseUrl.getData().getName(), responseUrl.getData().getSupply(),
				responseUrl.getData().getMaxSupply(), responseUrl.getData().getMarketCapUsd(),
				responseUrl.getData().getVolumeUsd24Hr(), responseUrl.getData().getPriceUsd(),
				responseUrl.getData().getChangePercent24Hr(), responseUrl.getData().getVwap24Hr(),
				responseUrl.getData().getExplorer());
	}
}
