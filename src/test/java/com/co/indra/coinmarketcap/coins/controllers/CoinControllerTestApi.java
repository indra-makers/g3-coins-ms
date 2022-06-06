package com.co.indra.coinmarketcap.coins.controllers;

import com.co.indra.coinmarketcap.coins.config.ErrorCodes;
import com.co.indra.coinmarketcap.coins.config.RestTemplateConfig;
import com.co.indra.coinmarketcap.coins.config.Routes;
import com.co.indra.coinmarketcap.coins.external_api.coincap.model.BodyResponseCoinCap;
import com.co.indra.coinmarketcap.coins.external_api.coincap.model.BodyResponseListCoinCap;
import com.co.indra.coinmarketcap.coins.external_api.coincap.model.CoinCapModel;
import com.co.indra.coinmarketcap.coins.external_api.coincap.repositoryRest.CoinCapRest;
import com.co.indra.coinmarketcap.coins.model.entities.Coin;
import com.co.indra.coinmarketcap.coins.model.responses.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class CoinControllerTestApi {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private RestTemplate restTemplate;

    @Value("${api.coincap.url}")
    private String apiUrl;


    //@Test
    public void getCoinsApiExternal() throws Exception {
        List<CoinCapModel> coinCapModels = new ArrayList<>();
        coinCapModels.add(new CoinCapModel("bitcoin", 5, "BTC", "Bitcoin", 56d, 800d, 50d,
                2000d, 4000d, 20d, 10d, "www.btc.com"));
        coinCapModels.add(new CoinCapModel("ethereum", 11, "ETH", "Ethereum", 56d, 800d, 50d,
                2000d, 4000d, 20d, 10d, "www.eth.com"));
        coinCapModels.add(new CoinCapModel("cardano", 10, "ADA", "Cardano", 56d, 800d, 50d,
                2000d, 4000d, 20d, 10d, "www.cardano.com"));
        coinCapModels.add(new CoinCapModel("shiba-inu", 3, "SHIB", "Shiba Inu", 56d, 800d, 50d,
                2000d, 4000d, 20d, 10d, "www.shiba.com"));

        BodyResponseListCoinCap mockedBody = new BodyResponseListCoinCap(coinCapModels, 1343434d);
        ResponseEntity<BodyResponseListCoinCap> entity = new ResponseEntity(mockedBody, HttpStatus.OK);

        Mockito.when(restTemplate.getForEntity(
                apiUrl,
                BodyResponseListCoinCap.class
        )).thenReturn(entity);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(Routes.COINS_PATH)
                .contentType(MediaType.APPLICATION_JSON);

        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        Assertions.assertEquals(200, response.getStatus());

        Coin[] coins = objectMapper.readValue(response.getContentAsString(), Coin[].class);
        Assertions.assertEquals("BTC", coins[0].getSymbol());
        Assertions.assertEquals("ETH", coins[1].getSymbol());
        Assertions.assertEquals("ADA", coins[2].getSymbol());
        Assertions.assertEquals("SHIB", coins[3].getSymbol());
    }

    //@Test
    public void getNoCoinsApiExternal() throws Exception {
        ResponseEntity<BodyResponseListCoinCap> entity = new ResponseEntity(HttpStatus.NOT_FOUND);

        Mockito.when(restTemplate.getForEntity(
                Mockito.anyString(),
                Mockito.<Class<BodyResponseListCoinCap>>any()
        )).thenReturn(entity);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(Routes.COINS_PATH)
                .contentType(MediaType.APPLICATION_JSON);

        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        Assertions.assertEquals(404, response.getStatus());

        String textResponseCoinHistory = response.getContentAsString();

        ErrorResponse error = objectMapper.readValue(textResponseCoinHistory, ErrorResponse.class);
        Assertions.assertEquals("NOT_FOUND", error.getCode());
        Assertions.assertEquals(ErrorCodes.ERROR_COINCAP_API.getMessage(), error.getMessage());
    }

    //@Test
    public void getCoinsBasicApiExternalBySymbol() throws Exception {
        CoinCapModel coinCapModel = new CoinCapModel("bitcoin", 5, "BTC", "Bitcoin", 56d, 800d, 50d,
                2000d, 4000d, 20d, 10d, "www.btc.com");

        BodyResponseCoinCap mockedBody = new BodyResponseCoinCap(coinCapModel, 1343434d);
        ResponseEntity<BodyResponseCoinCap> entity = new ResponseEntity(mockedBody, HttpStatus.OK);

        Mockito.when(restTemplate.getForEntity(
                Mockito.anyString(),
                Mockito.<Class<BodyResponseCoinCap>>any()
        )).thenReturn(entity);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(Routes.COINS_PATH+Routes.COIN_CURRENT_BY_SYMBOL, "BTC")
                .contentType(MediaType.APPLICATION_JSON);

        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        Assertions.assertEquals(200, response.getStatus());

        Coin coins = objectMapper.readValue(response.getContentAsString(), Coin.class);
        Assertions.assertEquals("BTC", coins.getSymbol());
    }

}
