SET GLOBAL time_zone = '+3:00';
create database informacion;
create user director identified by 'director';
grant all privileges on informacion.* to 'director';
use informacion;
create table datos (id int auto_increment primary key, nombre varchar(100) not null, valor varchar(100) not null);
