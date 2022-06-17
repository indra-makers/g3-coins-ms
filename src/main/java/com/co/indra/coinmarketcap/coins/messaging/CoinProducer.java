package com.co.indra.coinmarketcap.coins.messaging;

import com.co.indra.coinmarketcap.coins.model.entities.Coin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.co.indra.coinmarketcap.coins.external_api.coincap.model.CoinCapModel;
import com.fasterxml.jackson.databind.ObjectMapper;


@Component
public class CoinProducer {
      @Autowired
      private RabbitTemplate rabbitTemplate;

      @Autowired
      private ObjectMapper objectMapper;

      public void sendCoin(Coin coin) {
         try {
            String message= objectMapper.writeValueAsString(coin);
            rabbitTemplate.convertAndSend("portafolio_coin_queue",message);
            rabbitTemplate.convertAndSend("watchlist_coin_queue",message);
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
}

