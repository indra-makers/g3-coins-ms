package com.co.indra.coinmarketcap.coins.coinApi;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.co.indra.coinmarketcap.coins.model.responses.coinApi.CoinApiResponse;

@Component
public class CoinExternalApi {
	private final RestTemplate restTemplate;

	public CoinExternalApi(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	public CoinApiResponse getPostsPlainJSON() {
		String url = "https://api.coincap.io/v2/assets/";
		return this.restTemplate.getForObject(url, CoinApiResponse.class);

	}

}
