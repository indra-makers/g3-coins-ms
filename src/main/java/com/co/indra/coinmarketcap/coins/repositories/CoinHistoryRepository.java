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
        coinHistory.setIdHistory(rs.getLong("id_history"));
        coinHistory.setSymbol(rs.getString("symbol"));
        coinHistory.setIdCoin(rs.getLong("id_coin"));
        coinHistory.setDateReg(rs.getDate("date_reg"));
        coinHistory.setHigh(rs.getDouble("high"));
        coinHistory.setLow(rs.getDouble("low"));
        coinHistory.setClosePrice(rs.getDouble("close_price"));
        coinHistory.setVolume(rs.getDouble("volume"));
        coinHistory.setMarketCap(rs.getDouble("market_cap"));
        return coinHistory;
    }

}

@Repository
public class CoinHistoryRepository {

    @Autowired
    private JdbcTemplate template;
    
    
    public void createHistoryCoin (CoinHistory coinHistory) {
    	
    	template.update("INSERT INTO tbl_coin_histories(symbol,id_coin,high,low,close_price,volume,market_cap values(?,?,?,?,?,?,?,?)",
    			coinHistory.getSymbol(),coinHistory.getIdCoin(),coinHistory.getHigh(),coinHistory.getLow(),coinHistory.getClosePrice(),coinHistory.getVolume(),coinHistory.getMarketCap());
    	
    	
    }
    
    
    
    


}