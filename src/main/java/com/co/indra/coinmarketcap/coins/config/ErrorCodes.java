package com.co.indra.coinmarketcap.coins.config;

public enum ErrorCodes {
    COIN_WITH_SYMBOL_EXISTS("Coin with that symbol already exists", "001"),
    COIN_NOT_FOUND("Coin not found", "0002"),
    COIN_CATEGORY_NOT_FOUND("Category does not exist", "004"),
    HISTORY_NOT_FOUND("History does not exist", "003"),
    INVALID_CATEGORY("Invalid category","006");

    String message;
    String code;

    ErrorCodes(String message, String code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}