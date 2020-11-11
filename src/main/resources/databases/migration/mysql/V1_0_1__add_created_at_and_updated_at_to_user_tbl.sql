ALTER TABLE `demo`.`user`
ADD COLUMN `created_at` TIMESTAMP NULL AFTER `name`,
ADD COLUMN `updated_at` TIMESTAMP NULL AFTER `created_at`;

