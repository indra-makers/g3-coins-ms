package com.co.indra.coinmarketcap.coins.repositories;

import com.co.indra.coinmarketcap.coins.model.entities.Coin;
import org.flywaydb.core.internal.jdbc.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class CoinRepository {

    @Autowired
    private JdbcTemplate template;


}

class CoinRowMapper implements RowMapper<Coin> {


    @Override
    public Coin mapRow(ResultSet rs, int rowNum) throws SQLException {
        Coin coin = new Coin();
        coin.setSymbol(rs.getString("symbol"));
        coin.setNameCoin(rs.getString("nameCoin"));
        coin.setIcon(rs.getString("icon"));
        coin.setPrice(rs.getDouble("price"));
        coin.setDailyVariation(rs.getDouble("dailyVariation"));
        coin.setWeeklyVariation(rs.getDouble("weeklyVariation"));
        coin.setMarketCap(rs.getDouble("marketCap"));
        coin.setDaily_vol(rs.getDouble("dailyVol"));
        coin.setCirculatingSupply(rs.getDouble("circulatingSupply"));
        return coin;
    }
}
