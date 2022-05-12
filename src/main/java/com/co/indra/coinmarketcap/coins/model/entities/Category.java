package com.co.indra.coinmarketcap.coins.model.entities;

import java.io.Serializable;

public class Category implements Serializable {
    private Long id_category;


    private String name_category;


    private String description;

    public Category() {
    }

    public Category(Long id_category, String name_category, String description) {
        this.id_category = id_category;
        this.name_category = name_category;
        this.description = description;
    }

    public Long getId_category() {
        return id_category;
    }

    public void setId_category(Long id_category) {
        this.id_category = id_category;
    }

    public String getName_category() {
        return name_category;
    }

    public void setName_category(String name_category) {
        this.name_category = name_category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
