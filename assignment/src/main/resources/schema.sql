DROP TABLE IF EXISTS stock;
DROP TABLE IF EXISTS stock_price;
DROP TABLE IF EXISTS stock_views;
DROP TABLE IF EXISTS stock_trading_volume;

CREATE TABLE stock(
    id bigint(10) NOT NULL AUTO_INCREMENT,
    code varchar(10),
    name varchar(50),
    PRIMARY KEY (code)
);

CREATE TABLE stock_price(
    stock_code varchar(10),
    price int(20),
    create_time timestamp NOT NULL default now()
);

CREATE TABLE stock_views(
    stock_code varchar(10),
    view int(20),
    create_time timestamp NOT NULL default now()
);

CREATE TABLE stock_trading_volume(
    stock_code varchar(10),
    volume int(10),
    create_time timestamp NOT NULL default now()
);
