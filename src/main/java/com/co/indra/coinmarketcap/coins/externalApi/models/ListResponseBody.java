package com.co.indra.coinmarketcap.coins.externalApi.models;

import java.io.Serializable;
import java.util.List;

public class ListResponseBody implements Serializable {

    private List<CoinModel> data;
    private Double timestamp;

    public ListResponseBody() {}

    public ListResponseBody(List<CoinModel> data, Double timestamp) {
        this.data = data;
        this.timestamp = timestamp;
    }

    public List<CoinModel> getData() {
        return data;
    }

    public void setCoin(List<CoinModel> data) {
        this.data = data;
    }

    public Double getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Double timestamp) {
        this.timestamp = timestamp;
    }
}
