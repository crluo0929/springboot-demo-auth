create table if not exists book(
	id int auto_increment,
	title varchar(60) not null,
	isbn varchar(50) not null,
	author varchar(20) not null,
	lang varchar(10) not null,
	publish date
);

create table if not exists user(
    id int auto_increment,
    name varchar(20) not null,
    mail varchar(50),
    tel varchar(10),
    pass varchar(60) not null
) ;