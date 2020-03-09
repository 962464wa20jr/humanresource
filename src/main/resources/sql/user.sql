drop table if exists t_user;

/*==============================================================*/
/* Table: t_user                                                */
/*==============================================================*/
create table t_user
(
   id                   bigint(40) not null,
   staff_code           varchar(40),
   password             varchar(40),
   birthday             date,
   school               varchar(40),
   place_of_domicile    varchar(40),
   id_card_number       varchar(40),
   phone_number         varchar(11),
   address              varchar(40),
   name             	varchar(20),
   country              varchar(40),
   nation               varchar(40),
   department_id        bigint(40),
   create_time          date,
   update_time          date,
   status               tinyInt(2),
   salt                 varchar(40),
   last_login_time      date,
   sex                  tinyInt(2),
   primary key (id)
)ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

alter table t_user comment '员工表，记录员工档案的基本信息';

alter table t_user add constraint FK_Reference_1 foreign key (department_id)
      references t_department (id) on delete restrict on update restrict;
