
use manager;
drop table router;
create table router 
(
id int primary key auto_increment,
name varchar(255),
neighbor varchar(255),
delay double
);

insert into router values (null, '·����1','·����2',0.2);
insert into router values (null, '·����1','·����4',0.1);
insert into router values (null, '·����2','·����1',0.3);
insert into router values (null, '·����2','·����3',0.4);
insert into router values (null, '·����3','·����2',0.1);
insert into router values (null, '·����3','·����4',0.2);
insert into router values (null, '·����4','·����3',0.2);
insert into router values (null, '·����4','·����1',0.1);
