package com.co.indra.coinmarketcap.coins.services;

import com.co.indra.coinmarketcap.coins.config.ErrorCodes;
import com.co.indra.coinmarketcap.coins.exceptions.BusinessException;
import com.co.indra.coinmarketcap.coins.external_api.coincap.model.BodyResponseCoinCap;
import com.co.indra.coinmarketcap.coins.external_api.coincap.model.BodyResponseListCoinCap;
import com.co.indra.coinmarketcap.coins.external_api.coincap.model.CoinCapModel;
import com.co.indra.coinmarketcap.coins.external_api.coincap.repositoryRest.CoinCapRest;
import com.co.indra.coinmarketcap.coins.messaging.CoinProducer;
import com.co.indra.coinmarketcap.coins.model.entities.Coin;
import com.co.indra.coinmarketcap.coins.repositories.CoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CoinService {

    @Autowired
    CoinCapRest coinCapRest;
    
    @Autowired
    CoinRepository coinRepository;
    
    @Autowired
    CoinProducer coinProducer;


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

    public List<Coin> getCoinsExternal() {
        List<Coin> coins = new ArrayList<>();
        BodyResponseListCoinCap bodyResponseListCoinCap = coinCapRest.getAllCoins();
        for (CoinCapModel coinCapModel : bodyResponseListCoinCap.getData()) {
            coins.add(new Coin(coinCapModel.getSymbol(), coinCapModel.getName(), coinCapModel.getId(), coinCapModel.getPriceUsd(), coinCapModel.getVwap24Hr(), coinCapModel.getChangePercent24Hr(), coinCapModel.getMarketCapUsd(), coinCapModel.getVolumeUsd24Hr(), coinCapModel.getSupply()));
        }
        return coins;
    }

    public Coin getCoinBySymbolId(String symbol) {
        BodyResponseCoinCap bodyResponseCoinCap = coinCapRest.getCoinBySymbol(symbol);
        CoinCapModel coin = bodyResponseCoinCap.getData();
        Coin coinModel = new Coin(bodyResponseCoinCap.getData().getSymbol(), bodyResponseCoinCap.getData().getName(),
                bodyResponseCoinCap.getData().getId(), bodyResponseCoinCap.getData().getPriceUsd(),
                bodyResponseCoinCap.getData().getVwap24Hr(), bodyResponseCoinCap.getData().getChangePercent24Hr(),
                bodyResponseCoinCap.getData().getMarketCapUsd(), bodyResponseCoinCap.getData().getVolumeUsd24Hr(), bodyResponseCoinCap.getData().getSupply());
        coinProducer.sendCoin(coinModel);
        return coinModel;
    }

    public Coin getCoinBasicBySymbolId(String symbol) {
        BodyResponseCoinCap bodyResponseCoinCap = coinCapRest.getCoinBySymbol(symbol);
        return new Coin(bodyResponseCoinCap.getData().getSymbol(), bodyResponseCoinCap.getData().getName(), bodyResponseCoinCap.getData().getId());
    }

}
