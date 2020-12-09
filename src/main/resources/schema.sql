CREATE TABLE IF NOT EXISTS `product` (
    product_id bigint primary key, 
    product_name varchar(255), 
    price double,
    description varchar(2000),
	materials varchar(255),
	dimensions varchar(255),
    weight double,
    etsy_url varchar(1000),
    category varchar(255)
)
AS SELECT * FROM CSVREAD('classpath:soaps.csv');
CREATE TABLE IF NOT EXISTS `image` AS SELECT * FROM CSVREAD('classpath:images.csv');