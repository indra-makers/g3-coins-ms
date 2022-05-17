create table public.tbl_coins(
    id_coin serial,
    symbol varchar(3) NOT NULL UNIQUE,
    name_coin varchar(10) NOT NULL,
    icon varchar(255) NOT NULL,
    price decimal(20,5),
    daily_variation decimal(2,2),
    weekly_variation decimal(2,2),
    market_cap decimal(20,5),
    daily_vol decimal(20,5),
    circulating_supply decimal(20,5),
    PRIMARY KEY(id_coin, symbol)
);

create table public.tbl_coin_histories(
    id_history serial primary key,
    symbol varchar(3) NOT NULL,
    id_coin int NOT NULL,
    date_reg timestamp,
    high decimal(2,2),
    low decimal(2,2),
    close_price decimal(20,5),
    volume decimal(20,5),
    market_cap decimal(20,5),
    foreign key(symbol, id_coin) references tbl_coins(symbol, id_coin)
);

create table public.tbl_categories(
    id_category serial primary key,
    name_category varchar(15) NOT NULL,
    description varchar(255) NOT NULL
);

create table public.tbl_coin_categories(
    id_coin_category serial primary key,
    id_category int,
    symbol varchar(3) NOT NULL,
    foreign key(symbol) references tbl_coins(symbol),
    foreign key(id_category) references tbl_categories(id_category)
);



