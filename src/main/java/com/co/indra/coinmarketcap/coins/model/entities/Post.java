package com.co.indra.coinmarketcap.coins.model.entities;

import java.io.Serializable;

public class Post implements Serializable{

   

      private int userId;
      private int id;
      private String title;
      private String body;
      
      
      
      public int getUserId() {
         return userId;
      }
      public void setUserId(int userId) {
         this.userId = userId;
      }
      public int getId() {
         return id;
      }
      public void setId(int id) {
         this.id = id;
      }
      public String getTitle() {
         return title;
      }
      public void setTitle(String title) {
         this.title = title;
      }
      public String getBody() {
         return body;
      }
      public void setBody(String body) {
         this.body = body;
      }
      
      

      // getters and setters
  
   
}
