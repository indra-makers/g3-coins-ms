package com.co.indra.coinmarketcap.coins.controllers;

import com.co.indra.coinmarketcap.coins.config.Routes;
import com.co.indra.coinmarketcap.coins.external_api.coincap.model.BodyResponseListCoinCap;
import com.co.indra.coinmarketcap.coins.external_api.coincap.model.CoinCapModel;
import com.co.indra.coinmarketcap.coins.external_api.coincap.repositoryRest.CoinCapRest;
import com.co.indra.coinmarketcap.coins.model.entities.Coin;
import com.co.indra.coinmarketcap.coins.services.CoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import javax.validation.Valid;


@RestController
@RequestMapping(Routes.COINS_PATH)
public class CoinController {

   @Autowired
   CoinService coinService;

   @PostMapping
   public void createBasicCoin(@Valid @RequestBody Coin coin) {
      coinService.createBasicCoin(coin);
   }

   @GetMapping(Routes.COINS_BASIC_BY_ID)
   public Page<Coin> getCoinsPaged(@PageableDefault(page = 0, size = 10) Pageable pageable) {
      return (Page<Coin>) coinService.findPagedCoins((Pageable) pageable);
   }
   @GetMapping
   public BodyResponseListCoinCap getCoinsExternal(){
      return coinService.getCoinsExternal();
   }
   @GetMapping(Routes.COINS_SYMBOL_EXTERNAL)
   public CoinCapModel getCoinBySymbolId(@PathVariable("symbol") String symbol){
      return coinService.getCoinBySymbolId(symbol);
   }

}
