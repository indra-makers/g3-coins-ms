package com.co.indra.coinmarketcap.coins.services;

import com.co.indra.coinmarketcap.coins.config.ErrorCodes;
import com.co.indra.coinmarketcap.coins.exceptions.BusinessException;
import com.co.indra.coinmarketcap.coins.external_api.coincap.model.BodyResponseListCoinCap;
import com.co.indra.coinmarketcap.coins.external_api.coincap.model.CoinCapModel;
import com.co.indra.coinmarketcap.coins.external_api.coincap.repositoryRest.CoinCapRest;
import com.co.indra.coinmarketcap.coins.model.entities.Coin;
import com.co.indra.coinmarketcap.coins.repositories.CoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoinService {

   @Autowired
   CoinRepository coinRepository;
   @Autowired
   CoinCapRest coinCapRest;

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

   public BodyResponseListCoinCap getCoinsExternal(){
      return coinCapRest.getAllCoins();
   }
   public CoinCapModel getCoinBySymbolId(String symbol){
      return coinCapRest.getIdBySymbol(symbol);
   }

}
