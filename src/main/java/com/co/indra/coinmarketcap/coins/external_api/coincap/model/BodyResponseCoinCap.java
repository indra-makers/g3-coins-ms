package com.co.indra.coinmarketcap.coins.external_api.coincap.model;

import java.io.Serializable;

public class BodyResponseCoinCap implements Serializable {
    private CoinCapModel data;
    private Double timestamp;

    public BodyResponseCoinCap() {
    }

    public BodyResponseCoinCap(CoinCapModel data, Double timestamp) {
        this.data = data;
        this.timestamp = timestamp;
    }

    public CoinCapModel getData() {
        return data;
    }

    public void setData(CoinCapModel data) {
        this.data = data;
    }

    public Double getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Double timestamp) {
        this.timestamp = timestamp;
    }
}
