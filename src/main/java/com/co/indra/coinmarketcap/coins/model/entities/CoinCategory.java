package com.co.indra.coinmarketcap.coins.model.entities;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

public class CoinCategory implements Serializable {

    private Long idCoinCategory;

    private Long idCategory;

    @Pattern(regexp = "[A-Z]{3}")
    private String symbol;

    public CoinCategory() {
    }

    public CoinCategory(Long id, Long idCategory, String symbol) {
        this.idCoinCategory = id;
        this.idCategory = idCategory;
        this.symbol = symbol;
    }

    public Long getId() {
        return idCoinCategory;
    }

    public void setId(Long id) {
        this.idCoinCategory = id;
    }

    public Long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Long idCategory) {
        this.idCategory = idCategory;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
