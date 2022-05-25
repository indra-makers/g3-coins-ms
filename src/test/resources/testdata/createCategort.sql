insert into tbl_categories(id_category,name_category,description) values(1,'Solana','great');
insert into tbl_categories(id_category,name_category,description) values(2,'World','great');

INSERT INTO tbl_coins(symbol, name_coin, icon) values('BIC', 'BIC COIN', 'N/A');
INSERT INTO tbl_coins(symbol, name_coin, icon) values('ETH', 'ETHERUM', 'N/A');

INSERT INTO tbl_coin_categories(id_category, symbol) values(1, 'BIC');
INSERT INTO tbl_coin_categories(id_category, symbol) values(2, 'ETH');

create view category_coin
			as
SELECT  _intermediate.id_category,_coins.name_coin,_intermediate.symbol 
	from tbl_categories _coinCategory
	INNER JOIN tbl_coin_categories _intermediate on _intermediate.id_category =_coinCategory.id_category
	INNER JOIN tbl_coins _coins on _coins.symbol= _intermediate.symbol