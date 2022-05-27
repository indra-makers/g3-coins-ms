package com.co.indra.coinmarketcap.coins.restCoin;

import java.util.List;

public class RespuestaModelResp {
    private List<ModelResp> Data;
    private Long timestamp;

    public RespuestaModelResp() {
    }

    public RespuestaModelResp(List<ModelResp> data, Long timestamp) {
        Data = data;
        this.timestamp = timestamp;
    }

    public List<ModelResp> getData() {
        return Data;
    }

    public void setData(List<ModelResp> data) {
        Data = data;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
