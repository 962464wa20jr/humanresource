drop table if exists sys_role;

/*==============================================================*/
/* Table: sys_role                                              */
/*==============================================================*/
create table sys_role
(
   id                   bigint(40) not null,
   role                 varchar(20),
   description          varchar(40),
   primary key (id)
)ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

alter table sys_role comment '职位表，记录职位编号';
