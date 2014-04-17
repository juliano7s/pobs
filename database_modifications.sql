ALTER TABLE `clients` ADD `cpf` VARCHAR(11) NOT NULL AFTER `name`, ADD `address` VARCHAR(1000) NULL AFTER `cpf`;

CREATE USER 'pobs'@'localhost' IDENTIFIED BY PASSWORD '*BDB887B7477F549087D35D381C84B144CE71586D'; GRANT USAGE ON *.* TO 'pobs'@'localhost' IDENTIFIED BY PASSWORD '*BDB887B7477F549087D35D381C84B144CE71586D'; GRANT ALL PRIVILEGES ON `pobs`.* TO 'pobs'@'localhost' WITH GRANT OPTION;

ALTER TABLE `pobs`.`clients` 
CHANGE COLUMN `cpf` `cpf` VARCHAR(11) NULL ,
CHANGE COLUMN `address` `address` VARCHAR(1000) NULL ;