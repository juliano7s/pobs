ALTER TABLE `clients` ADD `cpf` VARCHAR(11) NOT NULL AFTER `name`, ADD `address` VARCHAR(1000) NULL AFTER `cpf`;

CREATE USER 'pobs'@'localhost' IDENTIFIED BY PASSWORD '*BDB887B7477F549087D35D381C84B144CE71586D'; GRANT USAGE ON *.* TO 'pobs'@'localhost' IDENTIFIED BY PASSWORD '*BDB887B7477F549087D35D381C84B144CE71586D'; GRANT ALL PRIVILEGES ON `pobs`.* TO 'pobs'@'localhost' WITH GRANT OPTION;

ALTER TABLE `pobs`.`clients` 
CHANGE COLUMN `cpf` `cpf` VARCHAR(11) NULL ,
CHANGE COLUMN `address` `address` VARCHAR(1000) NULL ;

ALTER TABLE `pobs`.`orders`
ADD COLUMN `status` VARCHAR(15) NULL DEFAULT 'INPROGRESS' COMMENT 'Values:' /* comment truncated */ /*INPROGRESS
READY
DELIVERED*/ AFTER `ownerid`;

ALTER TABLE `pobs`.`clients`
CHANGE COLUMN `clientid` `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ;

ALTER TABLE `pobs`.`orders`
CHANGE COLUMN `orderid` `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ;

ALTER TABLE `pobs`.`owners`
CHANGE COLUMN `ownerid` `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ;

CREATE TABLE `pobs`.`client_phones` (
  `clientid` INT(10) UNSIGNED NOT NULL,
  `phone` VARCHAR(45) NULL,
  PRIMARY KEY (`clientid`));
  
  ALTER TABLE `pobs`.`client_phones` 
CHANGE COLUMN `clientid` `clientid` INT(10) NOT NULL ,
DROP PRIMARY KEY;
