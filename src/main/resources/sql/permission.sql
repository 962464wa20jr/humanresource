drop table if exists sys_permission;

/*==============================================================*/
/* Table: sys_permission                                        */
/*==============================================================*/
create table sys_permission
(
   id                   bigint(40) not null,
   permission_name       varchar(40),
   permission           varchar(40),
   url                  varchar(40),
   primary key (id)
)ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

alter table sys_permission comment '权限表，记录权限类型';
