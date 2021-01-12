create table if not exists book(
	id int auto_increment,
	title varchar(60),
	isbn varchar(50),
	author varchar(20),
	lang varchar(10),
	publish date
);