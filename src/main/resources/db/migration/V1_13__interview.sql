CREATE TABLE IF NOT EXISTS `tb_interview` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `job_vacancy_id` bigint(20) NOT NULL,
  `applicant_id` bigint(20) NOT NULL,
  `professional_id` bigint(20) NOT NULL,
  `opinion` varchar(1000) NOT NULL,
  `recommendation` varchar(1000) NOT NULL,
  `complementary_knowledge` varchar(1000) NOT NULL,
  `approved` varchar(150) NOT NULL,
  `interview_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` varchar(255) NOT NULL,
  `create_by` varchar(255) NOT NULL DEFAULT 'system_user',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_by` varchar(255),
  `last_modified_date` datetime,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;