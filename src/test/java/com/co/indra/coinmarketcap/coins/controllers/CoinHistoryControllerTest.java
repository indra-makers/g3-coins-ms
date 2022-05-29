package com.co.indra.coinmarketcap.coins.controllers;

import java.util.List;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.co.indra.coinmarketcap.coins.config.Routes;
import com.co.indra.coinmarketcap.coins.model.entities.CoinHistory;
import com.co.indra.coinmarketcap.coins.model.responses.ErrorResponse;
import com.co.indra.coinmarketcap.coins.repositories.CoinHistoryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional

public class CoinHistoryControllerTest {

   @Autowired
   private MockMvc mockMvc;

   @Autowired
   private CoinHistoryRepository coinHistoryRepository;

   @Autowired
   private ObjectMapper objectMapper;

   @Test // Test creacion de historial de moneda sin ningun problema
   @Sql("/testdata/createCoinIntoTbl_Coins.sql")
   public void registerHistoryCoinHappyPath() throws Exception {

      MockHttpServletRequestBuilder requestCoinHistory = MockMvcRequestBuilders
            .post(Routes.COINS_PATH + Routes.COINS_HISTORY_BY_ID_COIN, 187)
            .content("{\r\n" + "\r\n" + "    \"symbol\": \"TPU\",\r\n" + "    \"high\": 1478,\r\n"
                  + "    \"low\": 7485,\r\n" + "    \"closePrice\": 1236,\r\n" + "    \"volume\": 5564,\r\n"
                  + "    \"marketCap\":4412\r\n" + "\r\n" + "}")
            .contentType(MediaType.APPLICATION_JSON);

      MockHttpServletResponse responseCoinHistory = mockMvc.perform(requestCoinHistory).andReturn().getResponse();
      Assertions.assertEquals(200, responseCoinHistory.getStatus());

      List<CoinHistory> coinHistoryList = coinHistoryRepository.findBySymbol("TPU");
      Assertions.assertEquals(1, coinHistoryList.size());

      CoinHistory coinHistoryToAssert = coinHistoryList.get(0);

      Assertions.assertEquals("TPU", coinHistoryToAssert.getSymbol());
      Assertions.assertEquals(187, coinHistoryToAssert.getIdCoin());

   }

   @Test // Test creacion de historial de moneda cuando ya existe un mismo historial
         // anteriormente
   @Sql("/testdata/createCoinIntoTbl_Coins.sql")
   @Sql("/testdata/createHistoryCoinIntoTbl_coin_histories.sql")
   public void registerHistoryCoinAlreadyExist() throws Exception {

      MockHttpServletRequestBuilder requestCoinHistory = MockMvcRequestBuilders
            .post(Routes.COINS_PATH + Routes.COINS_HISTORY_BY_ID_COIN, 187)
            .content("{\r\n" + "\r\n" + "    \"symbol\": \"TPU\",\r\n" + "    \"high\": 1478,\r\n"
                  + "    \"low\": 7485,\r\n" + "    \"closePrice\": 1236,\r\n" + "    \"volume\": 5564,\r\n"
                  + "    \"marketCap\":4412\r\n" + "\r\n" + "}")
            .contentType(MediaType.APPLICATION_JSON);

      MockHttpServletResponse responseCoinHistory = mockMvc.perform(requestCoinHistory).andReturn().getResponse();
      Assertions.assertEquals(412, responseCoinHistory.getStatus());

      String textResponseCoinHistory = responseCoinHistory.getContentAsString();

      ErrorResponse error = objectMapper.readValue(textResponseCoinHistory, ErrorResponse.class);
      Assertions.assertEquals("007", error.getCode());
      Assertions.assertEquals(
            "You are currently finding a currency history record equal to the one you are trying to enter",
            error.getMessage());

   }

   @Test // Test de creacion de historial de moneda con parametro malos en el body del
         // JSON en este caso se envia un simbolo de moneda en letras minusculas
   @Sql("/testdata/createCoinIntoTbl_Coins.sql")
   public void registerHistoryCoinWithBadParams() throws Exception {

      MockHttpServletRequestBuilder requestCoinHistory = MockMvcRequestBuilders
            .post(Routes.COINS_PATH + Routes.COINS_HISTORY_BY_ID_COIN, 187)
            .content("{\r\n" + "\r\n" + "    \"symbol\": \"tpu\",\r\n" + "    \"high\": 1478,\r\n"
                  + "    \"low\": 7485,\r\n" + "    \"closePrice\": 1236,\r\n" + "    \"volume\": 5564,\r\n"
                  + "    \"marketCap\":4412\r\n" + "\r\n" + "}")
            .contentType(MediaType.APPLICATION_JSON);

      MockHttpServletResponse responseCoinHistory = mockMvc.perform(requestCoinHistory).andReturn().getResponse();
      Assertions.assertEquals(400, responseCoinHistory.getStatus());

      String textResponseCoinHistory = responseCoinHistory.getContentAsString();

      ErrorResponse error = objectMapper.readValue(textResponseCoinHistory, ErrorResponse.class);
      Assertions.assertEquals("BAD_PARAMETERS", error.getCode());
   }

   @Test // Test de creacion de historial cuando la moneda no
         // existe (el ID de la moneda o el Simbolo no existe)
   @Sql("/testdata/createCoinIntoTbl_Coins.sql")
   public void registerHistoryCoinWhenIdCoinOrSymbolNotExist() throws Exception {

      MockHttpServletRequestBuilder requestCoinHistory = MockMvcRequestBuilders
            .post(Routes.COINS_PATH + Routes.COINS_HISTORY_BY_ID_COIN, 188)
            .content("{\r\n" + "\r\n" + "    \"symbol\": \"TUR\",\r\n" + "    \"high\": 1478,\r\n"
                  + "    \"low\": 7485,\r\n" + "    \"closePrice\": 1236,\r\n" + "    \"volume\": 5564,\r\n"
                  + "    \"marketCap\":4412\r\n" + "\r\n" + "}")
            .contentType(MediaType.APPLICATION_JSON);

      MockHttpServletResponse responseCoinHistory = mockMvc.perform(requestCoinHistory).andReturn().getResponse();
      Assertions.assertEquals(404, responseCoinHistory.getStatus());

      String textResponseCoinHistory = responseCoinHistory.getContentAsString();

      ErrorResponse error = objectMapper.readValue(textResponseCoinHistory, ErrorResponse.class);
      Assertions.assertEquals("NOT_FOUND", error.getCode());
      Assertions.assertEquals("Coin not found", error.getMessage());

   }

   @Test
   @Sql("/testdata/createCoinIntoTbl_Coins.sql")
   @Sql("/testdata/createHistoryCoinIntoTbl_coin_histories.sql")
   public void getAllCoinsPaged() throws Exception {

      MockHttpServletRequestBuilder requestCoinPaged = MockMvcRequestBuilders.get("/coins/CoinHistory?page=0&size=10")
            .contentType(MediaType.APPLICATION_JSON);

      MockHttpServletResponse responseCoinPaged = mockMvc.perform(requestCoinPaged).andReturn().getResponse();
      Assertions.assertEquals(200, responseCoinPaged.getStatus());

      List<CoinHistory> coinHistoryList = coinHistoryRepository.findBySymbol("TPU");

      // Libreria para deserializar,primer forma de comprobar una lista de datos
      Gson gson = new GsonBuilder().setDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz").create();
      
      
      
      String founderJson = gson.toJson(coinHistoryList);

      
      CoinHistory[] ArrCoinHistory = objectMapper.readValue(founderJson, CoinHistory[].class);
      Assertions.assertEquals(3, ArrCoinHistory.length);

      // Segunda forma de comprobar existencia de una lista de datos

      Assertions.assertEquals(3, coinHistoryList.size());

      CoinHistory coinHistoryToAssert1 = coinHistoryList.get(0);

      Assertions.assertEquals(1478, coinHistoryToAssert1.getHigh());
      Assertions.assertEquals(7485, coinHistoryToAssert1.getLow());

      CoinHistory coinHistoryToAssert2 = coinHistoryList.get(1);

      Assertions.assertEquals(8996, coinHistoryToAssert2.getHigh());
      Assertions.assertEquals(1241, coinHistoryToAssert2.getLow());

      CoinHistory coinHistoryToAssert3 = coinHistoryList.get(2);

      Assertions.assertEquals(7741, coinHistoryToAssert3.getHigh());
      Assertions.assertEquals(8895, coinHistoryToAssert3.getLow());

   }

}
