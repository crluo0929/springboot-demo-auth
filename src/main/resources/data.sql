-- prepare book sample data
insert into book(title,isbn,author,lang,publish) select * from (select '深入淺出設計模式','ISBN-1-1','伊麗莎白','中文','2004-10-01') as temp where not exists ( SELECT isbn FROM book WHERE isbn = 'ISBN-1-1' ) limit 1;
insert into book(title,isbn,author,lang,publish) select * from (select '深入淺出JavaScript編程','ISBN-1-2','伊麗莎白','中文','2004-03-20') as temp where not exists ( SELECT isbn FROM book WHERE isbn = 'ISBN-1-2') limit 1;
insert into book(title,isbn,author,lang,publish) select * from (select '深入淺出HTML與CSS、XHTML','ISBN-1-3','伊麗莎白','中文','2005-09-13') as temp where not exists ( SELECT isbn FROM book WHERE isbn = 'ISBN-1-3' ) limit 1;
insert into book(title,isbn,author,lang,publish) select * from (select 'Effective Java','ISBN-1-4','Joshua','英文','2003-01-10') as temp where not exists ( SELECT isbn FROM book WHERE isbn = 'ISBN-1-4' ) limit 1;
insert into book(title,isbn,author,lang,publish) select * from (select 'Head First Java','ISBN-1-5','Kathy','英文','2001-05-20') as temp where not exists ( SELECT isbn FROM book WHERE isbn = 'ISBN-1-5' ) limit 1;
insert into book(title,isbn,author,lang,publish) select * from (select 'Head First Design Patterns','ISBN-1-6','Eric','英文','2001-07-03') as temp where not exists ( SELECT isbn FROM book WHERE isbn = 'ISBN-1-6' ) limit 1;
insert into book(title,isbn,author,lang,publish) select * from (select 'Programming Abstractions in Java','ISBN-1-7','Eric','英文','2019-04-03') as temp where not exists ( SELECT isbn FROM book WHERE isbn = 'ISBN-1-7' ) limit 1;

-- prepare user sample data
-- password is ${name}+! encrypt by BCrypt, ex: admin -> admin!
insert into user(name,mail,tel,pass) select * from (select 'admin','admin@sample.com','0988111111','$2a$10$n2YyJ45XiwlQ7HzyLrE3a.b1W8e4TAEfTWaNaOC6p6UtAkfMoY1kq' ) as temp where not exists ( SELECT name FROM user WHERE name = 'admin' ) limit 1;
insert into user(name,mail,tel,pass) select * from (select 'user','user@sample.com','0988222222','$2a$10$8J4LhV1APpcWoX1tzCOAyeguhMrHX7d79T/Z9oV7YKg8i/IMJTeTy' ) as temp where not exists ( SELECT name FROM user WHERE name = 'user' ) limit 1;
insert into user(name,mail,tel,pass) select * from (select 'test','test@sample.com','0988333333','$2a$10$CnFRh.f6zB7i.n0tzp.xXOV0l9HFnknu2lgl.oOOZc0Z74KJXJrKO' ) as temp where not exists ( SELECT name FROM user WHERE name = 'test' ) limit 1;

set @adminid=(select id from user where name='admin');
set @userid=(select id from user where name='user');
set @testid=(select id from user where name='test');

insert into role(userid,role) select * from (select @adminid,'ADMIN' ) as temp where not exists ( SELECT userid FROM role WHERE userid = @adminid and role='ADMIN' ) limit 1;
insert into role(userid,role) select * from (select @adminid,'NORMAL' ) as temp where not exists ( SELECT userid FROM role WHERE userid = @adminid and role='NORMAL' ) limit 1;
insert into role(userid,role) select * from (select @adminid,'GUEST' ) as temp where not exists ( SELECT userid FROM role WHERE userid = @adminid and role='GUEST' ) limit 1;
insert into role(userid,role) select * from (select @userid,'NORMAL' ) as temp where not exists ( SELECT userid FROM role WHERE userid = @userid and role='NORMAL' ) limit 1;
insert into role(userid,role) select * from (select @userid,'GUEST' ) as temp where not exists ( SELECT userid FROM role WHERE userid = @userid and role='GUEST' ) limit 1;
insert into role(userid,role) select * from (select @testid,'GUEST' ) as temp where not exists ( SELECT userid FROM role WHERE userid = @testid and role='GUEST' ) limit 1;