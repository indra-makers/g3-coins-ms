package com.co.indra.coinmarketcap.coins.controllers;

import com.co.indra.coinmarketcap.coins.config.Routes;
import com.co.indra.coinmarketcap.coins.model.entities.CoinCategory;
import com.co.indra.coinmarketcap.coins.model.responses.coinCategory.CoinCategoryList;

import com.co.indra.coinmarketcap.coins.model.responses.coinCategory.CoinCategoryResponse;

import com.co.indra.coinmarketcap.coins.services.CoinCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

@RestController
@RequestMapping(Routes.COINS_CATEGORY_PATH)
public class CoinCategoryController {

	@Autowired
	CoinCategoryService coinCategoryService;

	@PostMapping
	public void createCoinCategory(@RequestBody CoinCategory coinCategory) {
		coinCategoryService.create(coinCategory);

	}

	/*
	 * @GetMapping(Routes.COIN_CATEGORY_BY_ID) public CoinCategoryResponse
	 * getCoinByCategory(@PathVariable int id) { return
	 * coinCategoryService.getCoinByCategory(id); }
	 */

	@GetMapping(Routes.COIN_CATEGORY_BY_ID)
	public Page<CoinCategoryList> getCoinByCategoryPaged(@PageableDefault(page = 0, size = 10) Pageable pageable,
			@PathVariable int id) {
		return coinCategoryService.findPagedCategory(pageable, id);
	}

}