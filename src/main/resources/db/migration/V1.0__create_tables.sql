create table public.tb_coin(
    symbol varchar(3) primary key,
    name_coin varchar(10) NOT NULL,
    icon varchar(255) NOT NULL,
    price decimal(20,5),
    daily_variation decimal(2,2),
    weekly_variation decimal(2,2),
    market_cap decimal(20,5),
    daily_vol decimal(20,5),
    circulating_supply decimal(20,5)
);

create table public.tb_coin_history(
    id_history int primary key,
    symbol varchar(3) NOT NULL,
    date_reg timestamp,
    high decimal(2,2),
    low decimal(2,2),
    close_price decimal(20,5),
    volume decimal(20,5),
    market_cap decimal(20,5)
);

create table public.tb_coin_category(
    id int primary key,
    id_category int,
    symbol varchar(3) NOT NULL
);

create table public.tb_category(
    id_category int primary key,
    name_category varchar(15) NOT NULL,
    description varchar(255) NOT NULL
);