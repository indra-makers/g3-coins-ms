package com.co.indra.coinmarketcap.coins.model.entities;

import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

public class CoinHistory implements Serializable {

   private Long idHistory;

   @Pattern(regexp = "[A-Z]{3}")
   private String symbol;

   private Long idCoin;

   private Date dateReg;

   private Double high;

   private Double low;

   private Double closePrice;

   private Double volume;

   private Double marketCap;

   public CoinHistory() {
   }

   public CoinHistory(String symbol, Long idCoin, Double high, Double low, Double closePrice, Double volume,
         Double marketCap) {
      super();
      this.symbol = symbol;
      this.idCoin = idCoin;
      this.high = high;
      this.low = low;
      this.closePrice = closePrice;
      this.volume = volume;
      this.marketCap = marketCap;
   }

   public Long getIdHistory() {
      return idHistory;
   }

   public void setIdHistory(Long idHistory) {
      this.idHistory = idHistory;
   }

   public String getSymbol() {
      return symbol;
   }

   public void setSymbol(String symbol) {
      this.symbol = symbol;
   }

   public Long getIdCoin() {
      return idCoin;
   }

   public void setIdCoin(Long idCoin) {
      this.idCoin = idCoin;
   }

   public Date getDateReg() {
      return dateReg;
   }

   public void setDateReg(Date dateReg) {
      this.dateReg = dateReg;
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

   public Double getClosePrice() {
      return closePrice;
   }

   public void setClosePrice(Double closePrice) {
      this.closePrice = closePrice;
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
