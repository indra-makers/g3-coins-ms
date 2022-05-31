package com.co.indra.coinmarketcap.coins.externalApi.repository;

import com.co.indra.coinmarketcap.coins.externalApi.models.CoinModel;
import com.co.indra.coinmarketcap.coins.externalApi.models.ListResponseBody;
import com.co.indra.coinmarketcap.coins.externalApi.models.ResponseBody;
import com.co.indra.coinmarketcap.coins.model.entities.Coin;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;


@Service
public class CoinRest {

    private final RestTemplate template;
    private Map<String, String> map = new HashMap<String, String>();

    @PostConstruct
    public void staticMap(){
        String url= "https://api.coincap.io/v2/assets";
        ListResponseBody listResponseBody = this.template.getForObject(url, ListResponseBody.class);
        for(CoinModel coinModel: listResponseBody.getData())
        {
            map.put(coinModel.getSymbol(), coinModel.getId());
        }
    }

    public CoinRest(RestTemplateBuilder restTemplateBuilder){
        this.template=restTemplateBuilder.build();
    }

    public ListResponseBody getCoins(){
        String url= "https://api.coincap.io/v2/assets";
        return this.template.getForObject(url, ListResponseBody.class);
    }

    public Coin getCoinBySymbol(String symbol){
        String url = "https://api.coincap.io/v2/assets/" + map.get(symbol);
        ResponseBody responseBody =  this.template.getForObject(url, ResponseBody.class);
        return new Coin(responseBody.getData().getSymbol(),responseBody.getData().getName(),responseBody.getData().getExplorer(),
                responseBody.getData().getPriceUsd(),responseBody.getData().getVwap24Hr(),responseBody.getData().getChangePercent24Hr(),
                responseBody.getData().getMarketCapUsd(),responseBody.getData().getVolumeUsd24Hr(),responseBody.getData().getSupply());
    }
}
