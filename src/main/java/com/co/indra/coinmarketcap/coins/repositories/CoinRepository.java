package com.co.indra.coinmarketcap.coins.repositories;

import com.co.indra.coinmarketcap.coins.model.entities.Coin;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

class CoinRowMapper implements RowMapper<Coin> {

   @Override
   public Coin mapRow(ResultSet rs, int rowNum) throws SQLException {
      Coin coin1 = new Coin();
      coin1.setIdCoin(rs.getLong("id_coin"));
      coin1.setSymbol(rs.getString("symbol"));
      coin1.setNameCoin(rs.getString("name_coin"));
      coin1.setIcon(rs.getString("icon"));
      coin1.setPrice(rs.getDouble("price"));
      coin1.setDailyVariation(rs.getDouble("daily_variation"));
      coin1.setWeeklyVariation(rs.getDouble("weekly_variation"));
      coin1.setMarketCap(rs.getDouble("market_cap"));
      coin1.setDaily_vol(rs.getDouble("daily_vol"));
      coin1.setCirculatingSupply(rs.getDouble("circulating_supply"));
      return coin1;
   }

}

@Repository
public class CoinRepository {

   @Autowired
   private JdbcTemplate template;

   public void createBasicCoin(@NotNull Coin coin) {
      template.update("INSERT INTO tbl_coins(symbol, name_coin, icon) values(?,?,?)", coin.getSymbol(),
            coin.getNameCoin(), coin.getIcon());
   }



 

   public List<Coin> findBySymbol(String symbol) {
      return template.query("SELECT * FROM tbl_coins WHERE symbol=?", new CoinRowMapper(), symbol);
   }

   public Coin mapRow(final ResultSet rs) throws SQLException {
      Coin coin = new Coin();
      // coin.setIdCoin(rs.getLong("id_coin"));
      coin.setSymbol(rs.getString("symbol"));
      coin.setNameCoin(rs.getString("name_coin"));
      coin.setIcon(rs.getString("icon"));

      return coin;
   }

   public List<Coin> findAll(Sort sort) {

      Sort.Order order = sort.toList().get(0);

      return template.query(
            "SELECT * FROM tbl_coins ORDER BY " + order.getProperty() + " " + order.getDirection().name(),
            (rs, rowNum) -> mapRow(rs));
   }

   public int count() {
      return template.queryForObject("SELECT count(*) FROM tbl_coins", Integer.class);
   }

   public Page<Coin> findAllPage(Pageable page) {
      Sort.Order order = !page.getSort().isEmpty() ? page.getSort().toList().get(0) : Sort.Order.by("symbol");
      List<Coin> coins = template.query("SELECT symbol, name_coin, icon FROM tbl_coins ORDER BY " + order.getProperty() + " "
            + order.getDirection().name() + " LIMIT " + page.getPageSize() + " OFFSET " + page.getOffset(),
            (rs, rowNum) -> mapRow(rs));
      return new PageImpl<Coin>(coins, page, count());
   }

  /* public Page<Coin> findAllCoinsPaged(Pageable pageable) {
      Sort.Order order = !pageable.getSort().isEmpty() ? pageable.getSort().toList().get(0) : Sort.Order.by("id_symbolCoin");
      List<Coin> coins = template.query("SELECT id_symbolCoin, nameCoin, iconCoin FROM tbl_coins ORDER BY "
                      + order.getProperty() + " " + order.getDirection().name()
                      + " LIMIT " + pageable.getPageSize() + " OFFSET " + pageable.getOffset(),
              (rs, rowNum) -> mapCoinResult(rs));
      return new PageImpl<Coin>(coins, pageable, count());
   }*/

}
