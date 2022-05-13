package com.co.indra.coinmarketcap.coins.repositories;

import com.co.indra.coinmarketcap.coins.model.entities.CoinHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;

class CoinHistoryRowMapper implements RowMapper<CoinHistory> {

    @Override
    public CoinHistory mapRow(ResultSet rs, int rowNum) throws SQLException {
        CoinHistory coinHistory = new CoinHistory();
        coinHistory.setIdHistory(rs.getLong("idHistory"));
        coinHistory.setSymbol(rs.getString("symbol"));
        coinHistory.setDate(rs.getDate("dateReg"));
        coinHistory.setHigh(rs.getDouble("high"));
        coinHistory.setLow(rs.getDouble("low"));
        coinHistory.setClose(rs.getDouble("closePrice"));
        coinHistory.setVolume(rs.getDouble("volume"));
        coinHistory.setMarketCap(rs.getDouble("marketCap"));
        return coinHistory;
    }

}

@Repository
public class CoinHistoryRepository {

    @Autowired
    private JdbcTemplate template;


}