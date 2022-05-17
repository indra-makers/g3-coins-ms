package com.co.indra.coinmarketcap.coins.controllers;

import com.co.indra.coinmarketcap.coins.services.CoinHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CoinHistoryController {

    @Autowired
    CoinHistoryService coinHistoryService;

}
