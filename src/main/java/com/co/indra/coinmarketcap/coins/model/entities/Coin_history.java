package com.co.indra.coinmarketcap.coins.model.entities;

import java.io.Serializable;
import java.util.Date;

public class Coin_history implements Serializable {

    private Long history;

    private String symbol;

    private Date date_reg;

    private Double high;

    private Double low;

    private Double close_price;

    private Double volume;

    private Double market_cap;

    public Coin_history() {
    }

    public Coin_history(Long history, String symbol, Date date, Double high, Double low, Double close, Double volume, Double market_cap) {
        this.history = history;
        this.symbol = symbol;
        this.date_reg = date;
        this.high = high;
        this.low = low;
        this.close_price = close;
        this.volume = volume;
        this.market_cap = market_cap;
    }

    public Long getHistory() {
        return history;
    }

    public void setHistory(Long history) {
        this.history = history;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Date getDate() {
        return date_reg;
    }

    public void setDate(Date date) {
        this.date_reg = date;
    }

    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public Double getClose() {
        return close_price;
    }

    public void setClose(Double close) {
        this.close_price = close;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Double getMarket_cap() {
        return market_cap;
    }

    public void setMarket_cap(Double market_cap) {
        this.market_cap = market_cap;
    }
}
