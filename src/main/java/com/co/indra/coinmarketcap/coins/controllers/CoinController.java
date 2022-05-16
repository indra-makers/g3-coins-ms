package com.co.indra.coinmarketcap.coins.controllers;

import com.co.indra.coinmarketcap.coins.model.entities.Coin;
import com.co.indra.coinmarketcap.coins.repositories.CoinRepository;
import com.co.indra.coinmarketcap.coins.services.CoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.awt.print.Pageable;

@RestController
public class CoinController {

    @Autowired
    CoinService coinService;

    @Autowired
    CoinRepository coinRepository;

    @PostMapping
    public void createBasicCoin(@Valid @RequestBody Coin coin){coinService.createBasicCoin(coin);}

    @GetMapping(path = "/coins/page")
    Page<Coin> loadCoinsPage(Pageable pageable){
        return coinRepository.findAllPag((org.springframework.data.domain.Pageable) pageable);
    }
}
