package com.co.indra.coinmarketcap.coins.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.co.indra.coinmarketcap.coins.model.responses.coinApi.CoinApiList;
import com.co.indra.coinmarketcap.coins.model.responses.coinApi.CoinApiResponse;
import com.co.indra.coinmarketcap.coins.repositories.CoinApiRepository;
import com.co.indra.coinmarketcap.coins.repositories.CoinRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CoinApiService {
	private final RestTemplate restTemplate;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private CoinApiRepository coinApiRepository;

	public CoinApiService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	/*
	 * public String coinData(String idSymbol) {
	 * 
	 * String apiList = coinApiRepository.apiList(idSymbol).get(0).getNameCoin();
	 * String url = "https://api.coincap.io/v2/assets/" + apiList; return
	 * restTemplate.getForObject(url, String.class,2);
	 * 
	 * }
	 */

	public CoinApiResponse getAlgo(String idSymbol){
		String nameSymbol = coinApiRepository.apiList(idSymbol).get(0).getNameCoin();
		String url = "https://api.coincap.io/v2/assets/" + nameSymbol;
		return this.restTemplate.getForObject(url, CoinApiResponse.class);

	}

}
