package com.co.indra.coinmarketcap.coins.repositories;

import com.co.indra.coinmarketcap.coins.model.entities.Category;
import com.co.indra.coinmarketcap.coins.model.entities.Coin;
import org.flywaydb.core.internal.jdbc.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class CategoryRepository {

    class CategoryRowMapper implements RowMapper<Category> {

        @Autowired
        private JdbcTemplate template;
        @Override
        public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
            Category category = new Category();
            category.setIdCategory(rs.getLong("idCategory"));
            category.setNameCategory(rs.getString("nameCategory"));
            category.setDescription(rs.getString("description"));
            return category;
        }
    }


}
