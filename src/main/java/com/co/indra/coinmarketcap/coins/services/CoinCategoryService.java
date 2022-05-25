package com.co.indra.coinmarketcap.coins.services;

import com.co.indra.coinmarketcap.coins.model.entities.CoinCategory;
import com.co.indra.coinmarketcap.coins.model.responses.CoinCategoryList;
import com.co.indra.coinmarketcap.coins.model.responses.CoinCategoryResponse;
import com.co.indra.coinmarketcap.coins.repositories.CoinCategoryRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoinCategoryService {

	@Autowired
	CoinCategoryRepository coinCategoryRepository;

	public void create(CoinCategory coinCategory) {
		coinCategoryRepository.createCoinCategory(coinCategory);

	}

	public CoinCategoryResponse getCoinByCategory(int idCategory) {
		List<CoinCategoryList> categoryCoin = coinCategoryRepository.getCoinByCategory(idCategory);

		return new CoinCategoryResponse(categoryCoin);

	}

	public Page<CoinCategoryList> findPagedCategory(Pageable pageable, int idCategory) {
		Page<CoinCategoryList> coin = coinCategoryRepository.findAllPage(pageable, idCategory);

		return coin;
	}

}
