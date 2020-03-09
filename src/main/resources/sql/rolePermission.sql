drop table if exists sys_role_permittion;

/*==============================================================*/
/* Table: sys_role_permittion                                   */
/*==============================================================*/
create table sys_role_permittion
(
   id                   bigint(40) not null,
   permission_id        bigint(40),
   role_id              bigint(40),
   primary key (ID)
)ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

alter table sys_role_permittion comment '记录角色拥有的权限';

alter table sys_role_permittion add constraint FK_Reference_29 foreign key (permission_id)
      references sys_permission (id) on delete restrict on update restrict;

alter table sys_role_permittion add constraint FK_Reference_30 foreign key (role_id)
      references sys_role (id) on delete restrict on update restrict;
