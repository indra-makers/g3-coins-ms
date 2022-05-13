package com.co.indra.coinmarketcap.coins.repositories;


import com.co.indra.coinmarketcap.coins.model.entities.CoinCategory;
import org.flywaydb.core.internal.jdbc.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class CoinCategoryRepository {
    @Autowired
    private JdbcTemplate template;


}

class CoinCategoryRowMapper implements RowMapper<CoinCategory> {


    @Override
    public CoinCategory mapRow(ResultSet rs, int rowNum) throws SQLException {
        CoinCategory coinCategory = new CoinCategory();
        coinCategory.setId(rs.getLong("idCoinCategory"));
        coinCategory.setIdCategory(rs.getLong("idCategory"));
        coinCategory.setSymbol(rs.getString("symbol"));
        return coinCategory;
    }
}
