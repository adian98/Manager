
use manager;
drop table subnetflow;
create table subnetflow 
(
id int primary key auto_increment,
name varchar(255),
subnet varchar(255),
flow int
);
set names gbk;
insert into subnetflow values (null, '��������','�߼���250',11);
insert into subnetflow values (null, '��������','�߼���1',20);
insert into subnetflow values (null, '��������','�߼���55',31);

insert into subnetflow values (null, '�������','�߼���250',2);
insert into subnetflow values (null, '�������','�߼���3',12);
insert into subnetflow values (null, '�������','�߼���13',21);
insert into subnetflow values (null, '�������','�߼���22',15); 
