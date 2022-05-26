package com.co.indra.coinmarketcap.coins.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.indra.coinmarketcap.coins.model.responses.coinApi.CoinApiResponse;
import com.co.indra.coinmarketcap.coins.services.CoinApiService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController()
@RequestMapping("/coinApi")
public class CoinApiController {
	@Autowired
	CoinApiService coinApiService;

	@GetMapping("/{idCoin}")
	public CoinApiResponse get(@PathVariable(name = "idCoin") String idCoin){

		return coinApiService.getAlgo(idCoin);
	}

}
