package com.co.indra.coinmarketcap.coins.controllers;

import com.co.indra.coinmarketcap.coins.config.Routes;
import com.co.indra.coinmarketcap.coins.model.entities.CoinHistory;
import com.co.indra.coinmarketcap.coins.model.request.CoinHistoryRequest;
import com.co.indra.coinmarketcap.coins.services.CoinHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Routes.COINS_PATH)
public class CoinHistoryController {

   @Autowired
   CoinHistoryService coinHistoryService;

   // Creacion de historial de una moneda por medio del Id Coin
   @PostMapping(Routes.COINS_HISTORY_BY_ID_COIN)
   public void registerHistoryCoin(@RequestBody CoinHistoryRequest request, @PathVariable("idCoin") Long idCoin) {

      coinHistoryService.registerHistoryCoin(request.getSymbol(), idCoin, request.getHigh(), request.getLow(),
            request.getClosePrice(), request.getVolume(), request.getMarketCap());

   }

   @GetMapping("/CoinHistory")
   public Page<CoinHistory> getAllCoinsPaged(@PageableDefault(page = 0, size = 10) Pageable pageable) {

      return (Page<CoinHistory>) coinHistoryService.findAllCoinsHistoryPage((Pageable) pageable);
   }

}
