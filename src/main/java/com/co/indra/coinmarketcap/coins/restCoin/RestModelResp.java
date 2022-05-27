package com.co.indra.coinmarketcap.coins.restCoin;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestModelResp {
    private final RestTemplate restTemplate;

    public RestModelResp(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public RespuestaModelResp getPostsPlainJSON() {
        String url = "https://api.coincap.io/v2/assets";
        return this.restTemplate.getForObject(url, RespuestaModelResp.class);
    }
}
