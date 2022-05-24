package com.co.indra.coinmarketcap.coins.controllers;


import com.co.indra.coinmarketcap.coins.config.Routes;
import com.co.indra.coinmarketcap.coins.model.entities.Coin;
import com.co.indra.coinmarketcap.coins.model.responses.ErrorResponse;
import com.co.indra.coinmarketcap.coins.repositories.CoinRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class CoinControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CoinRepository coinRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Test
    public void createBasicCoinHappyPath() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(Routes.COINS_PATH)
                .content("{\n" +
                        "    \"symbol\": \"BIC\",\n" +
                        "    \"nameCoin\": \"BIC COIN\",\n" +
                        "    \"icon\": \"N/A\" \n" +
                        "}").contentType(MediaType.APPLICATION_JSON);

        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        Assertions.assertEquals(200, response.getStatus());

        List<Coin> coins = coinRepository.findBySymbol("BIC");
        Assertions.assertEquals(1, coins.size());

        Coin CoinToAssert = coins.get(0);

        Assertions.assertEquals("BIC COIN", CoinToAssert.getNameCoin());
        Assertions.assertEquals("N/A", CoinToAssert.getIcon());
    }

    @Test
    @Sql("/testdata/createCoinAlreadyExists.sql")
    public void createCoinThatAlreadyExist() throws Exception {


        //----la ejecucion de la prueba misma--------------
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(Routes.COINS_PATH)
                .content("{\n" +
                        "    \"symbol\": \"BIC\",\n" +
                        "    \"nameCoin\": \"BIC COIN\",\n" +
                        "    \"icon\": \"N/A\" \n" +
                        "}").contentType(MediaType.APPLICATION_JSON);

        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();

        Assertions.assertEquals(412, response.getStatus());

        String textResponse = response.getContentAsString();
        ErrorResponse error = objectMapper.readValue(textResponse, ErrorResponse.class);

        Assertions.assertEquals("001", error.getCode());
        Assertions.assertEquals("Coin with that symbol already exists", error.getMessage());
    }

    @Test
    @Sql("/testdata/insertCoinsToPage.sql")
    public void loadPageCoinHappyPath() throws Exception{
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(Routes.COINS_PATH+Routes.COINS_BASIC_BY_ID+"?page=0&size=4")
                .contentType(MediaType.APPLICATION_JSON);

        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();

        Assertions.assertEquals(200, response.getStatus());

        JsonNode nodes = objectMapper.readTree(response.getContentAsString());

        Coin[] data = objectMapper.readValue(nodes.get("content").toString(), Coin[].class);
        Assertions.assertEquals(4, data.length);

        Assertions.assertEquals(4, nodes.get("pageable").get("pageSize").asInt());
        Assertions.assertEquals(0, nodes.get("pageable").get("pageNumber").asInt());
    }

}
