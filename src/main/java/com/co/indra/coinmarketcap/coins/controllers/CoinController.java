package com.co.indra.coinmarketcap.coins.controllers;

import com.co.indra.coinmarketcap.coins.config.Routes;
import com.co.indra.coinmarketcap.coins.model.entities.Coin;
import com.co.indra.coinmarketcap.coins.repositories.CoinRepository;
import com.co.indra.coinmarketcap.coins.services.CoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.print.Pageable;

@RestController
@RequestMapping(Routes.COINS_PATH)
public class CoinController {

    @Autowired
    CoinService coinService;

    @Autowired
    CoinRepository coinRepository;

    @PostMapping
    public void createBasicCoin(@Valid @RequestBody Coin coin){coinService.createBasicCoin(coin);}

    @GetMapping(path = "/coins")
    public Page<Coin> loadCoinsPage(Pageable pageable){
        return coinRepository.findAllPage((org.springframework.data.domain.Pageable) pageable);
    }

    @GetMapping
    public Page<Coin> getCoinsPaged(@PageableDefault(page = 1, size = 2) Pageable pageable){
        return (Page<Coin>) coinService.findPagedCoins((org.springframework.data.domain.Pageable) pageable);
    }


}
