package com.co.indra.coinmarketcap.coins;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CoinsApplication {

   public static void main(String[] args) {
      SpringApplication.run(CoinsApplication.class, args);
   }
}
