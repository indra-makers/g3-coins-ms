create table public.tb_coin(
    symbol varchar(3) primary key,
    name_coin varchar(10) NOT NULL,
    icon varchar(255) NOT NULL,
    price Double,
    daily_variation Double,
    weekly_variation Double,
    market_cap Double,
    daily_vol Double,
    circulating_supply Double
)

create table public.tb_coin_history(
    id_history int primary key
    symbol varchar(3) NOT NULL,
    date_reg timestamp,
    high Double,
    low Double,
    close_price Double,
    volume Double,
    market_cap Double
)

create table public.tb_coin_category(
    id int primary key,
    id_category int,
    symbol varchar(3) NOT NUL
)

create table public.tb_category(
    id_category int primary key,
    name_category varchar(15) NOT NULL,
    description varchar(255) NOT NULL
)