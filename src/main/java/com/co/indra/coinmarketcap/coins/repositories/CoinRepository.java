package com.co.indra.coinmarketcap.coins.repositories;

import com.co.indra.coinmarketcap.coins.model.entities.Coin;
import org.jetbrains.annotations.NotNull;
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

@Repository
public class CoinRepository {

    @Autowired
    private JdbcTemplate template;

    public void createBasicCoin(@NotNull Coin coin){
        template.update("INSERT INTO tbl_coins(symbol, name_coin, icon) values(?,?,?)",
                coin.getSymbol(), coin.getNameCoin(), coin.getIcon());
    }

    public List<Coin> findBySymbol(String symbol){
        return template.query(
                "SELECT symbol, name_coin, icon FROM tbl_coins WHERE symbol=?",
             new CoinRowMapper(), symbol
        );
    }

    public Coin mapRow(final ResultSet rs) throws SQLException {
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

    public List<Coin> findAll(Sort sort) {

        Sort.Order order = sort.toList().get(0);

        return template.query("SELECT * FROM tbl_coins ORDER BY "+order.getProperty()+" "+order.getDirection().name(),
                (rs, rowNum) -> mapRow(rs));
    }


    public int count() {
        return template.queryForObject("SELECT count(*) FROM USER", Integer.class);
    }

    public Page<Coin> findAllPag(Pageable page) {
        Sort.Order order = !page.getSort().isEmpty() ? page.getSort().toList().get(0) : Sort.Order.by("symbol");
        List<Coin> coins = template.query("SELECT * FROM tbl_coins ORDER BY " + order.getProperty() + " "
                        + order.getDirection().name() + " LIMIT " + page.getPageSize() + " OFFSET " + page.getOffset(),
                (rs, rowNum) -> mapRow(rs));
        return new PageImpl<Coin>(coins, page, count());
    }
}
