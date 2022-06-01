package com.co.indra.coinmarketcap.coins.restCoin;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class RestModelResp {
    private final RestTemplate restTemplate;
    private Map<String, String> map = new HashMap<String, String>();

    @PostConstruct
    public void staticMap(){
        String url= "https://api.coincap.io/v2/assets";
        RespuestaModelResp respuestaModelResp = this.restTemplate.getForObject(url, RespuestaModelResp.class);
        for(ModelResp modelResp: respuestaModelResp.getData())
        {
            map.put(modelResp.getSymbol(), modelResp.getId());
        }
    }

    public RestModelResp(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public RespuestaModelResp getPostsPlainJSON() {
        String url = "https://api.coincap.io/v2/assets";
        return this.restTemplate.getForObject(url, RespuestaModelResp.class);
    }
}
