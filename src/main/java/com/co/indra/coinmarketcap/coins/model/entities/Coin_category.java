package com.co.indra.coinmarketcap.coins.model.entities;

import java.io.Serializable;

public class Coin_category implements Serializable {

    private Long id;

    private Long id_category;

    private String symbol;

    public Coin_category() {
    }

    public Coin_category(Long id, Long id_category, String symbol) {
        this.id = id;
        this.id_category = id_category;
        this.symbol = symbol;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_category() {
        return id_category;
    }

    public void setId_category(Long id_category) {
        this.id_category = id_category;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
