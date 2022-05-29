package com.co.indra.coinmarketcap.coins.model.entities;

import java.io.Serializable;
import java.util.List;


public class ListResponse implements Serializable {
   
   
   
   private List<ModelAssetsApiExternal> data;
   
   private Double timestamp;
   
   
   public ListResponse() {
      super();
   }


   public ListResponse(List<ModelAssetsApiExternal> data, Double timestamp) {
      super();
      this.data = data;
      this.timestamp = timestamp;
   }


   
   
   public List<ModelAssetsApiExternal> getData() {
      return data;
   }


   public void setData(List<ModelAssetsApiExternal> data) {
      this.data = data;
   }


   public Double getTimestamp() {
      return timestamp;
   }


   public void setTimestamp(Double timestamp) {
      this.timestamp = timestamp;
   }
   
   
   
   
   
   
   
   
   
   
   

}
