package com.co.indra.coinmarketcap.coins.model.entities;

import java.io.Serializable;
import java.util.Date;

public class CoinHistory implements Serializable {

    private Long idHistory;

    private String symbol;

    private Date dateReg;

    private Double high;

    private Double low;

    private Double closePrice;

    private Double volume;

    private Double marketCap;

    public CoinHistory() {
    }

    public CoinHistory(Long history, String symbol, Date date, Double high, Double low, Double close, Double volume, Double marketCap) {
        this.idHistory = history;
        this.symbol = symbol;
        this.dateReg = date;
        this.high = high;
        this.low = low;
        this.closePrice = close;
        this.volume = volume;
        this.marketCap = marketCap;
    }

    public Long getIdHistory() {
        return idHistory;
    }

    public void setIdHistory(Long history) {
        this.idHistory = history;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Date getDate() {
        return dateReg;
    }

    public void setDate(Date date) {
        this.dateReg = date;
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
        return closePrice;
    }

    public void setClose(Double close) {
        this.closePrice = close;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Double getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(Double marketCap) {
        this.marketCap = marketCap;
    }
}
