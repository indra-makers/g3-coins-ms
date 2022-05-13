package com.co.indra.coinmarketcap.coins.services;

import com.co.indra.coinmarketcap.coins.repositories.CoinHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoinHistoryService {

    @Autowired
    CoinHistoryRepository coinHistoryRepository;

}
