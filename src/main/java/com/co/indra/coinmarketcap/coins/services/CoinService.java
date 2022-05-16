package com.co.indra.coinmarketcap.coins.services;

import com.co.indra.coinmarketcap.coins.config.ErrorCodes;
import com.co.indra.coinmarketcap.coins.exceptions.BusinessException;
import com.co.indra.coinmarketcap.coins.model.entities.Coin;
import com.co.indra.coinmarketcap.coins.repositories.CoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoinService {

    @Autowired
    CoinRepository coinRepository;

    public void createBasicCoin(Coin coin){
        if(coinRepository.findBySymbol(coin.getSymbol()).isEmpty()){
            coinRepository.createBasicCoin(coin);
        }
        else{
            throw new BusinessException(ErrorCodes.COIN_WITH_SYMBOL_EXISTS);
        }
    }

}
