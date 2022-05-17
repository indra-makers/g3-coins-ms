create table public.tbl_coins(
    id_coin serial,
    symbol varchar(3) NOT NULL UNIQUE,
    name_coin varchar(10) NOT NULL,
    icon varchar(255) NOT NULL,
    price double precision NOT NULL,
    daily_variation double precision NOT NULL,
    weekly_variation double precision NOT NULL,
    market_cap double precision NOT NULL,
    daily_vol double precision NOT NULL,
    circulating_supply double precision NOT NULL,
    PRIMARY KEY(id_coin, symbol)
);

create table public.tbl_coin_histories(
    id_history serial primary key,
    symbol varchar(3) NOT NULL,
    id_coin serial NOT NULL,
    "date_reg" timestamp NOT NULL DEFAULT now(),
    high double precision NOT NULL,
    low double precision NOT NULL,
    close_price double precision NOT NULL,
    volume double precision NOT NULL,
    market_cap double precision NOT NULL,
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



