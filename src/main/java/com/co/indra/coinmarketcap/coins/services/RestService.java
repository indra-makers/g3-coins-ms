package com.co.indra.coinmarketcap.coins.services;


import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.co.indra.coinmarketcap.coins.model.entities.ListResponse;
import com.co.indra.coinmarketcap.coins.model.entities.Post;
import com.co.indra.coinmarketcap.coins.model.entities.ResponseOnlyAsset;



@Service
public class RestService {
   
   
   private final RestTemplate restTemplate;

   public RestService(RestTemplateBuilder restTemplateBuilder) {
       this.restTemplate = restTemplateBuilder.build();
   }
   
   
   
   //Metodo para realizar una peticion que contiene un constructor de tipo lista
   public ListResponse getAssetsCoins(){
      String url= "https://api.coincap.io/v2/assets";
      return this.restTemplate.getForObject(url, ListResponse.class);
  }

   
   public ResponseOnlyAsset getOnlyAsset(String id) {
      String url= "https://api.coincap.io/v2/assets/" + id;
      return this.restTemplate.getForObject(url, ResponseOnlyAsset.class);
      
      
   }
   
   
   

   
   public Post[] getPostsAsObject() {
      String url = "https://jsonplaceholder.typicode.com/posts";
      return this.restTemplate.getForObject(url, Post[].class);
  }
   
   
   public Post getPostWithUrlParameters() {
      String url = "https://jsonplaceholder.typicode.com/posts/{id}";
      return this.restTemplate.getForObject(url, Post.class, 1);
  }
   
   
   
   public Post getPostWithResponseHandling() {
      String url = "https://jsonplaceholder.typicode.com/posts/{id}";
      
      ResponseEntity<Post> response = this.restTemplate.getForEntity(url, Post.class, 1);
      if(response.getStatusCode() == HttpStatus.OK) {
          return response.getBody();
      } else {
          return null;
      }
  }
   
   
   
   

   
   

}
