create table if not exists book(
	id int auto_increment,
	title varchar(60) not null,
	isbn varchar(50) not null,
	author varchar(20) not null,
	lang varchar(10) not null,
	publish date,
	primary key(id)
);

create table if not exists user(
    id int auto_increment,
    name varchar(20) not null,
    mail varchar(50),
    tel varchar(10),
    pass varchar(60) not null,
    primary key(id)
) ;

create table if not exists role(
    id int auto_increment,
    userid int not null,
    role varchar(10),
    primary key(id),
    foreign key (userId) references user(id)
) ;