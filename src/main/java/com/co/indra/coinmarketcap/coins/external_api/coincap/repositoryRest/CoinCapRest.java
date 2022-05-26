package com.co.indra.coinmarketcap.coins.external_api.coincap.repositoryRest;

import com.co.indra.coinmarketcap.coins.external_api.coincap.model.BodyResponseListCoinCap;
import com.co.indra.coinmarketcap.coins.external_api.coincap.model.CoinCapModel;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;


@Service
public class CoinCapRest {

    private final RestTemplate restTemplate;


    public CoinCapRest(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public BodyResponseListCoinCap getAllCoins() {
        String url = "https://api.coincap.io/v2/assets";
        return this.restTemplate.getForObject(url, BodyResponseListCoinCap.class);
    }

    public BodyResponseListCoinCap getIdBySymbol(String symbol) {
        String url = "https://api.coincap.io/v2/assets";
        return restTemplate.getForObject(url, BodyResponseListCoinCap.class);
    }
}
