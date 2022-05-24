package com.co.indra.coinmarketcap.coins.controllers;

import com.co.indra.coinmarketcap.coins.config.Routes;
import com.co.indra.coinmarketcap.coins.model.entities.Category;
import com.co.indra.coinmarketcap.coins.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Routes.CATEGORY_PATH)
public class CategoryController {

	@Autowired
	CategoryService categoryService;

	@PostMapping
	public void registerCategory(@RequestBody Category category) {
		categoryService.create(category);

	}

}
