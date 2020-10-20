#------------------VIDEO 4-----------------


#show databases;

create database if not exists tutorial1;
use tutorial1;
create table if not exists users (username text);	
#show tables;
desc users;
insert into users(username) values ("Rusu Tudor");
insert into users(username) values ("Vicky");
select * from users;



#----------------video 5----------------
desc users;
drop table users;
create table if not exists users(id int, username text);
desc users;
insert into users(id,username) values (1,"Bob");
insert into users(id,username) values (1,"Vicky");
select * from users;

#----------------video 6----------------

desc users;
insert into users(id,username) values (2,null);
select * from users LIMIT 0, 1000;

insert into users(id,username) values (null,null);
select * from users LIMIT 0, 1000;

insert into users(id) values (3);
select * from users LIMIT 0, 1000;

DROP TABLE users;
create table users (id int not null, username text not null);
desc users;
#insert into users (id,username) values (2, null);
#insert into users (id) values (2);
#insert into users (username) values ("Someone");
select * from users;


#----------------------video 7------------------------------

show engines;
show table status;
create table test(id int) engine=MYISAM;
show table status;
drop table test;
set default_storage_engine=MYISAM;
show tables;
show table status;
show engines;
create table test(id int);
show table status;
set default_storage_engine=INNODB;
SHOW ENGINES;


#------------------VIDEO 8-------------------

select @@GLOBAL.SQL_MODE, @@SESSION.SQL_MODE;
drop table test;
desc users;
SET sql_mode = 'STRICT_ALL_TABLES';

#---------------VIDEO 9----------------------

show tables;
select * from users;
insert into users(id,username) values (1,"Bob");
insert into users(id,username) values (2,"Vicky");
select * from users;

select @@SESSION.SQL_SAFE_UPDATES;
set  SQL_SAFE_UPDATES = 0;
delete from users;
select * from users;

#------------------------VIDEO 10------------------

SHOW TABLES;
drop table users;
create table users(id int primary key, name text, email text);
desc users;

insert into users(id, name, email) values (0, "Bob", "Bob@dadafa.com");
insert into users(id, name, email) values (1, "Vick", "Vicky@dadafa.com");

select * from users;

#insert into users(id, name, email) values (0, "cscxzc", "Bob@dacsadafa.com");

#----------------------------video 11----------------------------------
drop table users;

create table users(id int primary key auto_increment, name text, email text);


insert into users (name) values ('Bob');
select * from users;

insert into users (name) values ('Vicky');
select * from users;

insert into users (id,name) values (3,'Vicky');
select * from users;
 #drop database tutorial1;  #ca la final de rulare, sa se pregateasca pentru cealalta rulare sa se ruleze de la 0.
