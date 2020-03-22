drop table if exists t_register;

/*==============================================================*/
/* Table: t_register                                            */
/*==============================================================*/
create table t_register
(
   id                   bigint(20) not null,
   apply_ids            bigint(20),
   approver_id          bigint(20),
   create_time          date,
   update_time          date,
   status               tinyint(2),
   primary key (id)
);

alter table t_register comment '记录申请注册职员的信息';

alter table t_register add constraint FK_Reference_42 foreign key (apply_ids)
      references t_user (id) on delete restrict on update restrict;

alter table t_register add constraint FK_Reference_43 foreign key (approver_id)
      references t_user (id) on delete restrict on update restrict;
