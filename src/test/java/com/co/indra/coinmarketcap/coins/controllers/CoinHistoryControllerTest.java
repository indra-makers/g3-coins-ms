package com.co.indra.coinmarketcap.coins.controllers;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.co.indra.coinmarketcap.coins.model.entities.CoinHistory;
import com.co.indra.coinmarketcap.coins.repositories.CoinHistoryRepository;
import com.co.indra.coinmarketcap.coins.repositories.CoinRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class CoinHistoryControllerTest {

   @Autowired
   private MockMvc mockMvc;

   @Autowired
   private CoinHistoryRepository coinHistoryRepository;

   @Autowired
   private CoinRepository coinRepository;

   @Test
   public void registerHistoryCoin() throws Exception {

      // Se crea una moneda en base de datos para realizar el test
      coinRepository.createCoinTest();

      MockHttpServletRequestBuilder requestCoinHistory = MockMvcRequestBuilders.post("/coins/187/CoinHistory")
            .content("{\r\n" + "\r\n" + "    \"symbol\": \"TPU\",\r\n" + "    \"high\": 25636,\r\n"
                  + "    \"low\": 78412,\r\n" + "    \"closePrice\": 12345,\r\n" + "    \"volume\": 7412,\r\n"
                  + "    \"marketCap\":8889\r\n" + "\r\n" + "}")
            .contentType(MediaType.APPLICATION_JSON);

      MockHttpServletResponse responseCoinHistory = mockMvc.perform(requestCoinHistory).andReturn().getResponse();
      Assertions.assertEquals(200, responseCoinHistory.getStatus());

      List<CoinHistory> coinHistoryList = coinHistoryRepository.findBySymbol("TPU");
      Assertions.assertEquals(1, coinHistoryList.size());

      CoinHistory coinHistoryToAssert = coinHistoryList.get(0);

      Assertions.assertEquals("TPU", coinHistoryToAssert.getSymbol());
      Assertions.assertEquals(187, coinHistoryToAssert.getIdCoin());

      coinHistoryRepository.deleteCoinHistoryBySymbol("TPU");

      coinRepository.deleteCoinTest("TPU");

   }

}
