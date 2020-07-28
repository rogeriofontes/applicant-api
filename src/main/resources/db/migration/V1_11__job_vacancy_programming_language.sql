CREATE TABLE IF NOT EXISTS `tb_job_vacancy_programing_language` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `job_vacancy_id` bigint(20) NOT NULL,
  `programing_language_id` bigint(20) NOT NULL,
  `create_by` varchar(255) NOT NULL DEFAULT 'system_user',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_by` varchar(255),
  `last_modified_date` datetime,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;