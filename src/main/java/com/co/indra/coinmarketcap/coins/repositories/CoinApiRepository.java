package com.co.indra.coinmarketcap.coins.repositories;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.co.indra.coinmarketcap.coins.model.entities.Coin;

class CoinApiRowMapper implements RowMapper<Coin> {

	@Override
	public Coin mapRow(ResultSet rs, int rowNum) throws SQLException {
		Coin coin1 = new Coin();
		coin1.setNameCoin(rs.getString("name_coin"));
		return coin1;
	}

}

@Repository
public class CoinApiRepository {

	@Autowired
	private JdbcTemplate template;

	public List<Coin> apiList(String idSymbol) {
		return template.query("SELECT name_coin from tbl_coins where symbol = ?", new CoinApiRowMapper(),
				idSymbol);
	}

}
