package com.co.indra.coinmarketcap.coins.external_api.coincap.repositoryRest;

import com.co.indra.coinmarketcap.coins.config.ErrorCodes;
import com.co.indra.coinmarketcap.coins.exceptions.NotFoundException;
import com.co.indra.coinmarketcap.coins.external_api.coincap.model.BodyResponseCoinCap;
import com.co.indra.coinmarketcap.coins.external_api.coincap.model.BodyResponseListCoinCap;
import com.co.indra.coinmarketcap.coins.external_api.coincap.model.CoinCapModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;


@Component
public class CoinCapRest {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.coincap.url}")
    private String apiUrl;

    private Map<String, String> mapSymbolId = new HashMap<String, String>();

    @PostConstruct
    public void initMap() {
        BodyResponseListCoinCap bodyResponseListCoinCap = getAllCoins();
        for (CoinCapModel coin : bodyResponseListCoinCap.getData()) {
            mapSymbolId.put(coin.getSymbol(), coin.getId());
        }
    }

    @Cacheable(value = "coin", cacheManager = "expire30seg", key = "", unless = "#result == null")
    public BodyResponseListCoinCap getAllCoins() {
        ResponseEntity<BodyResponseListCoinCap> response = restTemplate.getForEntity(apiUrl, BodyResponseListCoinCap.class);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new NotFoundException(ErrorCodes.ERROR_COINCAP_API);
        }
        return response.getBody();
    }

    @Cacheable(value = "coin", cacheManager = "expire30seg", key = "#symbol", unless = "#result == null")
    public BodyResponseCoinCap getCoinBySymbol(String symbol) {
        String url = apiUrl+"/"+mapSymbolId.get(symbol);
        ResponseEntity<BodyResponseCoinCap> response = restTemplate.getForEntity(url, BodyResponseCoinCap.class);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new NotFoundException(ErrorCodes.ERROR_COINCAP_API);
        }
        return response.getBody();
    }
}
