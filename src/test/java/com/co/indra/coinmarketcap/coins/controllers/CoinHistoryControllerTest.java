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
import com.co.indra.coinmarketcap.coins.model.entities.CoinHistory;
import com.co.indra.coinmarketcap.coins.repositories.CoinHistoryRepository;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional

public class CoinHistoryControllerTest {

   @Autowired
   private MockMvc mockMvc;

   @Autowired
   private CoinHistoryRepository coinHistoryRepository;

  

   @Test
   @Sql("/testdata/createCoinIntoTbl_Coins.sql")
   public void registerHistoryCoin() throws Exception {

 

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

     

   }
   
   @Test
   @Sql("/testdata/createCoinIntoTbl_Coins.sql")
   @Sql("/testdata/createHistoryCoinIntoTbl_coin_histories.sql")
   public void getAllCoinsPaged() throws Exception{
      
      MockHttpServletRequestBuilder requestCoinPaged = MockMvcRequestBuilders.get("/coins/CoinHistory?page=0&size=10")
            .contentType(MediaType.APPLICATION_JSON);
            
      MockHttpServletResponse responseCoinPaged = mockMvc.perform(requestCoinPaged).andReturn().getResponse();
      Assertions.assertEquals(200, responseCoinPaged.getStatus());
      
      
      List<CoinHistory> coinHistoryList = coinHistoryRepository.findBySymbol("BTC");
      Assertions.assertEquals(3, coinHistoryList.size());

      CoinHistory coinHistoryToAssert = coinHistoryList.get(0);

      Assertions.assertEquals("BTC", coinHistoryToAssert.getSymbol());
      Assertions.assertEquals(2, coinHistoryToAssert.getIdCoin());
      
      
   }
   
   
   
   
   

}
