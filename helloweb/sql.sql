create table tbl_user(
id int(11) unsigned not null auto_increment,
name varchar(50) not null,
password varchar(50) not null,
email varchar(50),
primary key(id))engine=InnoDB default charset=utf8;

insert into tbl_user(name,password,email) values('TOM','123456','T@WIN.COM');

select * from tbl_user;

set global time_zone='+8:00';

select * from mysql.user;

