package com.co.indra.coinmarketcap.coins.controllers;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.co.indra.coinmarketcap.coins.config.Routes;
import com.co.indra.coinmarketcap.coins.model.entities.Category;
import com.co.indra.coinmarketcap.coins.model.responses.coinCategory.CoinCategoryList;
import com.co.indra.coinmarketcap.coins.repositories.CategoryRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class CoinCategoryControllerTest {

	
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
@Autowired
	private CategoryRepository coinRepository;

	@Test
	@Sql("/testdata/createCoinCategory.sql")
	public void getCoinByCategoryPageable() throws Exception {
		// ----la ejecucion de la prueba misma--------------
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.get(Routes.COINS_CATEGORY_PATH + Routes.COIN_CATEGORY_BY_ID + "?page=0&size=1", 1)
				.contentType(MediaType.APPLICATION_JSON);

		MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
		// ------------ las verificaciones--------------------
		Assertions.assertEquals(200, response.getStatus());

		JsonNode nodes = objectMapper.readTree(response.getContentAsString());

		CoinCategoryList[] data = objectMapper.readValue(nodes.get("content").toString(), CoinCategoryList[].class);
		Assertions.assertEquals(1, data.length);

		Assertions.assertEquals(1, nodes.get("pageable").get("pageSize").asInt());
		Assertions.assertEquals(0, nodes.get("pageable").get("pageNumber").asInt());

	}
	
	
	@Test
	@Sql("/testdata/createCategory.sql")
	public void createCategoryHappyPath() throws Exception {
		// ----la ejecucion de la prueba misma--------------
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
            .post(Routes.CATEGORY_PATH)
            .content("{\n" +
                  "    \"nameCategory\": \"Solana\",\n" +
                  "    \"description\": \"great\"\n" +
                  "}").contentType(MediaType.APPLICATION_JSON);

    MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
    Assertions.assertEquals(200, response.getStatus());
    
    List<Category> category = coinRepository.findCategoryById(1);
    Assertions.assertEquals(1, category.size());

    Category categoryToAssert = category.get(0);

    Assertions.assertEquals("Solana", categoryToAssert.getNameCategory());
    Assertions.assertEquals("great", categoryToAssert.getDescription());
  

	}
}
