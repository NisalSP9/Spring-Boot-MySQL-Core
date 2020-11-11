ALTER TABLE `demo`.`user`
ADD COLUMN `password` VARCHAR(255) NOT NULL AFTER `username`,
ADD COLUMN `email` VARCHAR(50) NOT NULL AFTER `password`,
ADD COLUMN `contact_number` VARCHAR(20) NOT NULL AFTER `email`,
ADD COLUMN `status` TINYINT NOT NULL AFTER `contact_number`,
ADD COLUMN `privilege` INT NOT NULL AFTER `status`,
CHANGE COLUMN `name` `username` VARCHAR(255) NOT NULL ,
CHANGE COLUMN `updated_at` `updated_at` TIMESTAMP NULL DEFAULT NULL ;

