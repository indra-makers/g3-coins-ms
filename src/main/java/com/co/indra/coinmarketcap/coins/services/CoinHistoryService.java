package com.co.indra.coinmarketcap.coins.services;

import com.co.indra.coinmarketcap.coins.config.ErrorCodes;
import com.co.indra.coinmarketcap.coins.exceptions.BusinessException;
import com.co.indra.coinmarketcap.coins.exceptions.NotFoundException;
import com.co.indra.coinmarketcap.coins.model.entities.CoinHistory;
import com.co.indra.coinmarketcap.coins.repositories.CoinHistoryRepository;
import com.co.indra.coinmarketcap.coins.repositories.CoinRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CoinHistoryService {

   @Autowired
   private CoinHistoryRepository coinHistoryRepository;

   @Autowired
   private CoinRepository coinRepository;

   public void registerHistoryCoin(String symbol, Long idCoin, Double high, Double low, Double closePrice,
         Double volume, Double marketCap) {

      

      if (coinRepository.findByIdCoin(idCoin).isEmpty()) {

         throw new NotFoundException(ErrorCodes.COIN_NOT_FOUND);
      }
      
      if (coinRepository.findBySymbol(symbol).isEmpty()) {

         throw new NotFoundException(ErrorCodes.COIN_NOT_FOUND);
      }
      

      if (!coinHistoryRepository.findByAllAttributes(symbol, idCoin, high,
            low, closePrice, volume, marketCap).isEmpty()) {

         throw new BusinessException(ErrorCodes.COIN_HISTORY_EXIST);

      }

      coinHistoryRepository
            .createHistoryCoin(new CoinHistory(symbol, idCoin, high, low, closePrice, volume, marketCap));

   }

   
   public Page<CoinHistory> findAllCoinsHistoryPage(Pageable pageable) {

      Page<CoinHistory> coinHistory = coinHistoryRepository.findAllCoinsHistoryPage(pageable);
      return coinHistory;
   }

}
