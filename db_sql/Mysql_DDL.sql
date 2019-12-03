create table car_models_zs like t_car_models_zs;
insert into car_models_zs select * from t_car_models_zs;

create table car_models_ly like t_car_models_ly;
insert into car_models_ly select * from t_car_models_ly;


 --词集表
drop table if exists Wset_Weight;
create table Wset_Weight(
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    facture_name VARCHAR(32)  default '',
    wscore DOUBLE default 0,
    create_by VARCHAR(32) NOT NULL DEFAULT 'ADMIN',
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
    update_by VARCHAR(32) NOT NULL DEFAULT 'ADMIN',
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间'
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create unique index Wset_Weight_index on Wset_Weight(id);
create  index facture_name_index on Wset_Weight(facture_name);

   --特征向量表1
drop table if exists ly_fv_matrix;
create table ly_fv_matrix(
   id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
   ly_id int,
   ly_fv text  default '',
   ly_fv_wset VARCHAR(1024)  default '',
   score DOUBLE default 0,
   status varchar(1) default '0',
   create_by VARCHAR(32) NOT NULL DEFAULT 'ADMIN',
   create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
   update_by VARCHAR(32) NOT NULL DEFAULT 'ADMIN',
   update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间'
  )ENGINE=InnoDB DEFAULT CHARSET=utf8;

  create unique index ly_fv_matrix_index on ly_fv_matrix(id);

  --特征向量表2
drop table if exists zs_fv_matrix;
create table zs_fv_matrix(
   id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
   zs_id int,
   ly_id int,
   zs_fv text  default '',
   zs_fv_wset VARCHAR(1024)  default '',
   score DOUBLE default 0,
   status varchar(1) default '0',
   create_by VARCHAR(32) NOT NULL DEFAULT 'ADMIN',
   create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
   update_by VARCHAR(32) NOT NULL DEFAULT 'ADMIN',
   update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间'
  )ENGINE=InnoDB DEFAULT CHARSET=utf8;

  create unique index zs_fv_matrix_index on zs_fv_matrix(id);

