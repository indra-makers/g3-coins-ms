package com.co.indra.coinmarketcap.coins.model.entities;

import java.io.Serializable;



public class ResponseOnlyAsset implements Serializable{
   
   
   private ModelAssetsApiExternal data;
   
   
   private Double timestamp;


   public ResponseOnlyAsset() {
      super();
   }


   public ResponseOnlyAsset(ModelAssetsApiExternal data, Double timestamp) {
      super();
      this.data = data;
      this.timestamp = timestamp;
   }


   public ModelAssetsApiExternal getData() {
      return data;
   }


   public void setData(ModelAssetsApiExternal data) {
      this.data = data;
   }


   public Double getTimestamp() {
      return timestamp;
   }


   public void setTimestamp(Double timestamp) {
      this.timestamp = timestamp;
   }
   
   
   
   
   
   

}
