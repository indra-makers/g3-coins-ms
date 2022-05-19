package com.co.indra.coinmarketcap.coins.repositories;

import com.co.indra.coinmarketcap.coins.model.entities.CoinHistory;

import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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

   public CoinHistory mapCoinHistoryResult(final ResultSet rs) throws SQLException {
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

   public void createHistoryCoin(CoinHistory coinHistory) {

      template.update(
            "INSERT INTO tbl_coin_histories (symbol,id_coin,high,low,close_price,volume,market_cap) values(?,?,?,?,?,?,?)",
            coinHistory.getSymbol(), coinHistory.getIdCoin(), coinHistory.getHigh(), coinHistory.getLow(),
            coinHistory.getClosePrice(), coinHistory.getVolume(), coinHistory.getMarketCap());

   }

   public int count() {
      return template.queryForObject("SELECT count(*) FROM tbl_coin_histories", Integer.class);
   }

   public Page<CoinHistory> findAllCoinsHistoryPage(Pageable pageable) {
      Sort.Order order = !pageable.getSort().isEmpty() ? pageable.getSort().toList().get(0)
            : Sort.Order.by("id_history");
      List<CoinHistory> coinsHistory = template.query(
            "SELECT id_history,symbol,id_coin,date_reg,high,low,close_price,volume,market_cap FROM tbl_coin_histories ORDER BY "
                  + order.getProperty() + " " + order.getDirection().name() + " LIMIT " + pageable.getPageSize()
                  + " OFFSET " + pageable.getOffset(),
            (rs, rowNum) -> mapCoinHistoryResult(rs));
      return new PageImpl<CoinHistory>(coinsHistory, pageable, count());
   }

   public List<CoinHistory> findBySymbol(String symbol) {

      return template.query(
            "SELECT id_history,symbol,id_coin,date_reg,high,low,close_price,volume,market_cap FROM tbl_coin_histories WHERE symbol =?",
            new CoinHistoryRowMapper(), symbol);

   }



}