drop table if exists sys_user_role;

/*==============================================================*/
/* Table: sys_user_role                                         */
/*==============================================================*/
create table sys_user_role
(
   id                   bigint(40) not null,
   user_id              bigint(40),
   role_id              bigint(40),
   primary key (id)
)ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

alter table sys_user_role comment '员工和角色表之间的连接表';

alter table sys_user_role add constraint FK_Reference_25 foreign key (user_id)
      references t_user (id) on delete restrict on update restrict;

alter table sys_user_role add constraint FK_Reference_26 foreign key (role_id)
      references sys_role (id) on delete restrict on update restrict;
