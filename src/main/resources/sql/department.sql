drop table if exists t_department;

/*==============================================================*/
/* Table: t_department                                          */
/*==============================================================*/
create table t_department
(
   id                   bigint(40) not null,
   department_name      varchar(20),
   department_count     int,
   department_desc      text,
   create_time          date,
   update_time          date,
   primary key (id)
)ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

alter table t_department comment '记录部门基本情况';
