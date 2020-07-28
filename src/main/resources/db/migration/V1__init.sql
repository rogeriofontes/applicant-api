--ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'YOUR_ROOT_USER_PASSWORD';

create table `tb_user` (
	`id` bigint NOT NULL AUTO_INCREMENT,
	`name` varchar(255) NOT NULL,
    `email` varchar(255) NOT NULL,
	`password` varchar(255) NOT NULL,
	`status` varchar(255) NOT NULL,
	`create_by` varchar(255) NOT NULL DEFAULT 'system_user',
    `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `last_modified_by` varchar(255),
    `last_modified_date` datetime,
	primary key (id),
	constraint username_unique unique (email)
);

insert into tb_user (id, name, email, password, status) values (1, 'Rogério Fontes', 'fontestz@gmail.com', '$2a$10$4q2I1/BSLFOx64ji5oDz2uH.ZLOtQFi9N821ILDmjxO7wgt/gagnS', 'ATIVO');

create table `tb_profile` (
	`id` bigint NOT NULL AUTO_INCREMENT,
	`role` varchar(255),
	`status` varchar(255) NOT NULL,
	`create_by` varchar(255) NOT NULL DEFAULT 'system_user',
    `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `last_modified_by` varchar(255),
    `last_modified_date` datetime,
	primary key (id)
);

insert into tb_profile (id, role, status) values (1, 'USER', 'ATIVO');
insert into tb_profile (id, role, status) values (2, 'ADMIN', "ATIVO");

create table `user_profile` (
    `user_id` bigint(20) NOT NULL,
    `profile_id` bigint(20) NOT NULL
);

insert into user_profile (user_id, profile_id) values (1, 1);
insert into user_profile (user_id, profile_id) values (1, 2);