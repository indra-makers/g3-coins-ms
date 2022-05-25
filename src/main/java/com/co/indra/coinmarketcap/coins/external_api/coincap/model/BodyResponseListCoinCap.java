package com.co.indra.coinmarketcap.coins.external_api.coincap.model;

import java.io.Serializable;
import java.util.List;

public class BodyResponseListCoinCap implements Serializable {
    private List<CoinCapModel> data;
    private Double timestamp;

    public BodyResponseListCoinCap(List<CoinCapModel> data, Double timestamp) {
        this.data = data;
        this.timestamp = timestamp;
    }

    public BodyResponseListCoinCap() {
    }

    public List<CoinCapModel> getData() {
        return data;
    }

    public void setData(List<CoinCapModel> data) {
        this.data = data;
    }

    public Double getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Double timestamp) {
        this.timestamp = timestamp;
    }
}