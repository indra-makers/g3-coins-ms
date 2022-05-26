package com.co.indra.coinmarketcap.coins.config;

public class Routes {

   public static final String COINS_PATH = "/coins";

   public static final String COINS_BASIC_PATH = "/basic";

   public static final String COIN_BASIC_BY_SYMBOL = "/basic/{symbol}";

   public static final String COINS_BASIC_BY_ID = "/basic/by_id";

   public static final String COINS_CURRENT_PATH = "/current";

   public static final String COIN_CURRENT_BY_SYMBOL = "/current/{symbol}";

   public static final String COINS_CURRENT_BY_ID = "/current/by_id";

   public static final String COINS_HISTORY_PATH = "/history";

   public static final String COINS_HISTORY_BY_ID = "/history/by_id";

   public static final String COINS_HISTORY_BY_ID_COIN = "/{idCoin}/CoinHistory";

   public static final String COINS_HISTORY = "/CoinHistory";

   public static final String CATEGORY_PATH = "/category";

   public static final String CATEGORY_BY_ID = "/category/{id}";

   public static final String CATEGORIES_BY_ID = "/category/by_id";

   public static final String COINS_CATEGORY_PATH = "/coin_category";

   public static final String COIN_CATEGORY_BY_ID = "/coin_category/{id}";

   public static final String COINS_CATEGORIES_BY_ID = "/coin_category/by_id";

   public static final String SYMBOL_ID_PATH = "/{symbol}";

}
