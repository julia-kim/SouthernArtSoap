CREATE TABLE IF NOT EXISTS `product` AS SELECT * FROM CSVREAD('classpath:soaps.csv');
CREATE TABLE IF NOT EXISTS `image` AS SELECT * FROM CSVREAD('classpath:images.csv');