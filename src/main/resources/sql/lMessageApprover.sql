drop table if exists l_message_approver;

/*==============================================================*/
/* Table: l_message_approver                                    */
/*==============================================================*/
create table l_message_approver
(
   message_id           bigint(20) not null,
   approver_id          bigint(20) not null,
   is_done              tinyint(2),
   primary key (message_id, approver_id)
);

alter table l_message_approver comment '通知和受理人是多对多关系。故创建连接表';

alter table l_message_approver add constraint FK_Reference_40 foreign key (message_id)
      references sys_message (id) on delete restrict on update restrict;

alter table l_message_approver add constraint FK_Reference_41 foreign key (approver_id)
      references t_user (id) on delete restrict on update restrict;
