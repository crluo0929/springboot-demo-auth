-- prepare book sample data
insert into book values(default,'深入淺出設計模式','ISBN-1-1','伊麗莎白','中文','2004-10-01');
insert into book values(default,'深入淺出JavaScript編程','ISBN-1-2','伊麗莎白','中文','2004-03-20');
insert into book values(default,'深入淺出HTML與CSS、XHTML','ISBN-1-3','伊麗莎白','中文','2005-09-13');
insert into book values(default,'Effective Java','ISBN-1-4','Joshua','英文','2003-01-10');
insert into book values(default,'Head First Java','ISBN-1-5','Kathy','英文','2001-05-20');
insert into book values(default,'Head First Design Patterns','ISBN-1-6','Eric','英文','2001-07-03');
insert into book values(default,'Programming Abstractions in Java','ISBN-1-7','Eric','英文','2019-04-03');

-- prepare user sample data
-- password is ${name}+! encrypt by BCrypt, ex: admin -> admin!
insert into user values(default,'admin','admin@sample.com','0988111111','$2a$10$n2YyJ45XiwlQ7HzyLrE3a.b1W8e4TAEfTWaNaOC6p6UtAkfMoY1kq');
insert into user values(default,'user','user@sample.com','0988222222','$2a$10$8J4LhV1APpcWoX1tzCOAyeguhMrHX7d79T/Z9oV7YKg8i/IMJTeTy');
insert into user values(default,'test','','','$2a$10$CnFRh.f6zB7i.n0tzp.xXOV0l9HFnknu2lgl.oOOZc0Z74KJXJrKO');
insert into role values(default,select id from user where name='admin' ,'ADMIN') ;
insert into role values(default,select id from user where name='admin','NORMAL') ;
insert into role values(default,select id from user where name='admin','GUEST') ;
insert into role values(default,select id from user where name='user','NORMAL') ;
insert into role values(default,select id from user where name='user','GUEST') ;
insert into role values(default,select id from user where name='test','GUEST') ;