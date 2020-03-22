drop table if exists sys_message;

/*==============================================================*/
/* Table: sys_message                                           */
/*==============================================================*/
create table sys_message
(
   id                   bigint(20) not null,
   user_id              bigint(20),
   event                varchar(100),
   type                 tinyint(2),
   create_time          date,
   id_done              tinyint(2),
   primary key (id)
);

alter table sys_message comment '消息表，记录用户需要去处理的事项';

alter table sys_message add constraint FK_Reference_37 foreign key (user_id)
      references t_user (id) on delete restrict on update restrict;
