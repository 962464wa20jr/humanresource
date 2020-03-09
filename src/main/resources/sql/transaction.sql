drop table if exists sys_transaction;

/*==============================================================*/
/* Table: sys_transaction                                       */
/*==============================================================*/
create table sys_transaction
(
   type                 tinyint(2) not null,
   code                 tinyint(2) not null,
   name                 varchar(20),
   primary key (type, code)
);

alter table sys_transaction comment '记录事务的类型';
