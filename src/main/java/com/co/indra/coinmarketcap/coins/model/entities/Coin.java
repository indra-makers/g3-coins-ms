package com.co.indra.coinmarketcap.coins.model.entities;

import java.io.Serializable;

public class Coin implements Serializable {

    private String symbol;

    private String name_coin;

    private String icon;

    private Double price;

    private Double daily_variation;

    private Double weekly_variation;

    private Double market_cap;

    private Double daily_vol;

    private Double circulating_supply;

    public Coin() {
    }

    public Coin(String symbol, String name_coin, String icon, Double price, Double daily_variation, Double weekly_variation, Double market_cap, Double daily_vol, Double circulating_supply) {
        this.symbol = symbol;
        this.name_coin = name_coin;
        this.icon = icon;
        this.price = price;
        this.daily_variation = daily_variation;
        this.weekly_variation = weekly_variation;
        this.market_cap = market_cap;
        this.daily_vol = daily_vol;
        this.circulating_supply = circulating_supply;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName_coin() {
        return name_coin;
    }

    public void setName_coin(String name_coin) {
        this.name_coin = name_coin;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDaily_variation() {
        return daily_variation;
    }

    public void setDaily_variation(Double daily_variation) {
        this.daily_variation = daily_variation;
    }

    public Double getWeekly_variation() {
        return weekly_variation;
    }

    public void setWeekly_variation(Double weekly_variation) {
        this.weekly_variation = weekly_variation;
    }

    public Double getMarket_cap() {
        return market_cap;
    }

    public void setMarket_cap(Double market_cap) {
        this.market_cap = market_cap;
    }

    public Double getDaily_vol() {
        return daily_vol;
    }

    public void setDaily_vol(Double daily_vol) {
        this.daily_vol = daily_vol;
    }

    public Double getCirculating_supply() {
        return circulating_supply;
    }

    public void setCirculating_supply(Double circulating_supply) {
        this.circulating_supply = circulating_supply;
    }
}
