package com.co.indra.coinmarketcap.coins.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.co.indra.coinmarketcap.coins.model.responses.coinApi.CoinApi;
import com.co.indra.coinmarketcap.coins.services.CoinApiService;


@RestController()
@RequestMapping("/coinApi")
public class CoinApiController {
	
	@Autowired
	CoinApiService coinApiService;

	@GetMapping("/{symbol}")
	public CoinApi get(@PathVariable(name = "symbol") String idCoin){

		return coinApiService.getCoinsBySymbol(idCoin);
	}

}
