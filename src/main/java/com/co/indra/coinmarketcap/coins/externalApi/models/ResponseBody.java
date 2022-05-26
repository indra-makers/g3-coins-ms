package com.co.indra.coinmarketcap.coins.externalApi.models;

import java.io.Serializable;

public class ResponseBody implements Serializable {
    private CoinModel data;
    private Double timestamp;

    public ResponseBody() {}

    public ResponseBody(CoinModel data, Double timestamp){
        this.data=data;
        this.timestamp=timestamp;
    }

    public CoinModel getData() {
        return data;
    }

    public void setData(CoinModel data) {
        this.data = data;
    }

    public Double getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Double timestamp) {
        this.timestamp = timestamp;
    }
}
