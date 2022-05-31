package com.co.indra.coinmarketcap.coins.external_api.coincap.repositoryRest;

import com.co.indra.coinmarketcap.coins.external_api.coincap.model.BodyResponseCoinCap;
import com.co.indra.coinmarketcap.coins.external_api.coincap.model.BodyResponseListCoinCap;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



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

    public BodyResponseCoinCap getCoinBySymbol(String symbol) {
        String url = "https://api.coincap.io/v2/assets/" + symbol;
        return this.restTemplate.getForObject(url, BodyResponseCoinCap.class);
    }
}
