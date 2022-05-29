package com.co.indra.coinmarketcap.coins.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.co.indra.coinmarketcap.coins.config.Routes;
import com.co.indra.coinmarketcap.coins.model.entities.ListResponse;
import com.co.indra.coinmarketcap.coins.model.entities.ModelAssetsApiExternal;
import com.co.indra.coinmarketcap.coins.model.entities.Post;
import com.co.indra.coinmarketcap.coins.model.entities.ResponseOnlyAsset;
import com.co.indra.coinmarketcap.coins.services.RestService;


@RestController
@RequestMapping(Routes.COINS_PATH)
public class ControllerApiExternal {
   
   @Autowired
   private RestService restService;
   
  /*
   @GetMapping("/AssetsApiExternal")
   public Post[] getCoinsExternal() {
      return restService.getPostsAsObject();
   }*/
   
   /*
   @GetMapping("/AssetsApiExternal")
   public Post getCoinsExternal() {
      return restService.getPostWithUrlParameters();
   }*/
   
   /*
   @GetMapping("/AssetsApiExternal")
   public Post getCoinsExternal() {
      return restService.getPostWithResponseHandling();
   }
   */
   
   @GetMapping("/AssetsApiExternal")
   public ListResponse getCoinsExternal() {
      return restService.getAssetsCoins();
   }
   
   @GetMapping("/OnlyAssetApiExternal/{id}")
   public ResponseOnlyAsset getOnlyCoinExternal(@PathVariable ("id")String id) {
      return restService.getOnlyAsset(id);
   }
   
   

}
