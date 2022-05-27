package com.co.indra.coinmarketcap.coins.services;

import com.co.indra.coinmarketcap.coins.config.ErrorCodes;
import com.co.indra.coinmarketcap.coins.exceptions.BusinessException;
import com.co.indra.coinmarketcap.coins.model.entities.Coin;
import com.co.indra.coinmarketcap.coins.repositories.CoinRepository;
import com.co.indra.coinmarketcap.coins.restCoin.RespuestaModelResp;
import com.co.indra.coinmarketcap.coins.restCoin.RestModelResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CoinService {
   @Autowired
   private RestModelResp restModelResp;
   @Autowired
   private CoinRepository coinRepository;

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
   public Coin coinSymbol(String symbol){
      RespuestaModelResp respuestaModelResp = restModelResp.getPostsPlainJSON();
      for(int i=0; i<=respuestaModelResp.getData().size(); i++){
         if(respuestaModelResp.getData().get(i).getSymbol().equals(symbol)){
            return new Coin(respuestaModelResp.getData().get(i).getSymbol(),respuestaModelResp.getData().get(i).getName(),respuestaModelResp.getData().get(i).getExplorer(),
                    respuestaModelResp.getData().get(i).getPriceUsd(), respuestaModelResp.getData().get(i).getVwap24Hr(),respuestaModelResp.getData().get(i).getVolumeUsd24Hr(),
                    respuestaModelResp.getData().get(i).getMarketCapUsd(),respuestaModelResp.getData().get(i).getChangePercent24Hr(),respuestaModelResp.getData().get(i).getMaxSupply());
         }
      }
      return null;
   }
}
