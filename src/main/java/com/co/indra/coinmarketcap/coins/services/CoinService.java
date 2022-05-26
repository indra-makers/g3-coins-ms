package com.co.indra.coinmarketcap.coins.services;

import com.co.indra.coinmarketcap.coins.config.ErrorCodes;
import com.co.indra.coinmarketcap.coins.exceptions.BusinessException;
import com.co.indra.coinmarketcap.coins.externalApi.models.ListResponseBody;
import com.co.indra.coinmarketcap.coins.externalApi.models.ResponseBody;
import com.co.indra.coinmarketcap.coins.externalApi.repository.CoinRest;
import com.co.indra.coinmarketcap.coins.model.entities.Coin;
import com.co.indra.coinmarketcap.coins.repositories.CoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class CoinService {

   @Autowired
   CoinRepository coinRepository;

   @Autowired
   CoinRest coinRest;

   public void createBasicCoin(Coin coin) {
      if (coinRepository.findBySymbol(coin.getSymbol()).isEmpty()) {
         coinRepository.createBasicCoin(coin);
      } else {
         throw new BusinessException(ErrorCodes.COIN_WITH_SYMBOL_EXISTS);
      }
   }

   public Page<Coin> findPagedCoins(Pageable pageable) {
      Page<Coin> coin = coinRepository.findAllPage(pageable);
      return coin;
   }

   public Coin findCoinBySymbol(String symbol){
      return coinRest.getCoinBySymbol(symbol);
   }

   public ListResponseBody findAssets(){
      return coinRest.getCoins();
   }
}
