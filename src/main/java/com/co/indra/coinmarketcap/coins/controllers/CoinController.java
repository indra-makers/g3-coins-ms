package com.co.indra.coinmarketcap.coins.controllers;

import com.co.indra.coinmarketcap.coins.config.Routes;
import com.co.indra.coinmarketcap.coins.externalApi.models.ListResponseBody;
import com.co.indra.coinmarketcap.coins.externalApi.models.ResponseBody;
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
   public ListResponseBody findAssets(){
      return coinService.findAssets();
   }

   @GetMapping(Routes.SYMBOL_ID_PATH)
   public Coin getCoinWithSymbol(@PathVariable ("symbol") String symbol){
      return coinService.findCoinBySymbol(symbol);
   }
}
