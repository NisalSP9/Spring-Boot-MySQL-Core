CREATE TABLE `role_policies` (
  `role_id` bigint(20) NOT NULL,
  `policy_id` bigint(20) NOT NULL,
  PRIMARY KEY (`policy_id`,`role_id`),
  KEY `role_id` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

