package com.co.indra.coinmarketcap.coins.repositories;

import com.co.indra.coinmarketcap.coins.model.entities.Coin;
import com.co.indra.coinmarketcap.coins.model.entities.CoinCategory;
import com.co.indra.coinmarketcap.coins.model.responses.CoinCategoryList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

class CoinCategoryRowMapper implements RowMapper<CoinCategory> {

	@Override
	public CoinCategory mapRow(ResultSet rs, int rowNum) throws SQLException {
		CoinCategory coinCategory = new CoinCategory();
		coinCategory.setId(rs.getLong("id_coin_category"));
		coinCategory.setIdCategory(rs.getLong("id_category"));
		coinCategory.setSymbol(rs.getString("symbol"));
		return coinCategory;
	}

}

@Repository
public class CoinCategoryRepository {

	@Autowired
	private JdbcTemplate template;

	public void createCoinCategory(CoinCategory coinCategory) {
		template.update("INSERT INTO tbl_coin_categories(id_category,symbol) values(?,?)", coinCategory.getIdCategory(),
				coinCategory.getSymbol());
	}

	public List<CoinCategoryList> getCoinByCategory(int idCategory) {
		return template.query("select name_coin, symbol from category_coin where id_category=?",
				(rs, rn) -> new CoinCategoryList(rs.getString("name_coin"), rs.getString("symbol")), idCategory);
	}
	
	public int count() {
      return template.queryForObject("SELECT count(*) FROM category_coin", Integer.class);
   }
	
   public Page<CoinCategoryList> findAllPage(Pageable page, int idCategory) {
      Sort.Order order = !page.getSort().isEmpty() ? page.getSort().toList().get(0) : Sort.Order.by("symbol");
      List<CoinCategoryList> coins = template.query("SELECT name_coin, symbol FROM category_coin where id_category=? ORDER BY " + order.getProperty() + " "
            + order.getDirection().name() + " LIMIT " + page.getPageSize() + " OFFSET " + page.getOffset(),
            (rs, rowNum) -> new CoinCategoryList(rs.getString("name_coin"), rs.getString("symbol")),idCategory);
      return new PageImpl<CoinCategoryList>(coins, page, count());
   }

}