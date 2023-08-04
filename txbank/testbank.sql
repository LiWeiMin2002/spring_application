create database testBank;
use testBank;

create table accounts(
    accountId int primary key auto_increment,
    balance numeric(10,2)
);

create table oprecord(
    id int primary key auto_increment,
    accountId int references account(accountId),
    opmoney numeric (10,2),
    optime datetime,
    optype enum('deposite','withdraw',transfer)not null,
    transferId varchar(50)
);