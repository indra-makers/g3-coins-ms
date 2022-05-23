package com.co.indra.coinmarketcap.coins.services;

import com.co.indra.coinmarketcap.coins.model.entities.CoinHistory;
import com.co.indra.coinmarketcap.coins.repositories.CoinHistoryRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CoinHistoryService {

   @Autowired
   CoinHistoryRepository coinHistoryRepository;

   public void registerHistoryCoin(String symbol, Long idCoin, Double high, Double low, Double closePrice,
         Double volume, Double marketCap) {

      
      List<CoinHistory> coinHistoryByAllAttributtes = coinHistoryRepository.findByAllAttributes(symbol, idCoin, high, low, closePrice, volume, marketCap);
      
      if(!coinHistoryByAllAttributtes.isEmpty()) {
         
         throw new RuntimeException("You are currently finding a currency history record equal to the one you are trying to enter");
      }
      
      coinHistoryRepository
            .createHistoryCoin(new CoinHistory(symbol, idCoin, high, low, closePrice, volume, marketCap));

   }

   
   public Page<CoinHistory> findAllCoinsHistoryPage(Pageable pageable) {

      Page<CoinHistory> coinHistory = coinHistoryRepository.findAllCoinsHistoryPage(pageable);
      return coinHistory;
   }

}
