create database jwagajang;

USE JWAGAJANG;

create user 'jwagajang'@'%' identified by '1234';
grant all privileges on *.* to 'jwagajang'@'%';

create table table_user (
    user_id varchar(20),
    user_pw varchar(100),
    user_phone varchar(30),
    user_address varchar(100),
    user_adddetail varchar(100),
    user_email varchar(30),
    user_regdate datetime default current_timestamp,
    user_editdate datetime default current_timestamp,
    user_grade int(10)
);

create table table_notice (
    notice_code int,
    notice_label varchar(50),
    notice_title varchar(50),
    notice_regdate datetime default current_timestamp,
    notice_editdate datetime default current_timestamp,
    notice_content varchar(300),
    notice_count int
);

create table table_md (
    md_code int,
    md_name varchar(30),
    md_price int,
    md_dc int,
    md_stock int,
    img_main varchar(200),
    img_detail varchar(200),
    md_regdate datetime default current_timestamp,
    md_editdate datetime default current_timestamp,
    category_main varchar(30),
    category_sub varchar(30),
    md_ordercnt int,
    category_main_name varchar(30)
);

create table table_qna (
    qna_code int,
    qna_label varchar(20),
    qna_title varchar(50),
    user_id varchar(30),
    qna_regdate datetime default current_timestamp,
    qna_editdate datetime default current_timestamp,
    qna_content varchar(200),
    qna_count int,
    qna_reply varchar(100)
);

create table table_review (
    md_code int,
    user_id varchar(30)
);

create table table_order (
    mdpic varchar(50),
    mdname varchar(50),
    mdprice int,
    mdcode int,
    mdid varchar(30),
    order_name varchar(30),
    mobile varchar(30),
    address varchar(50),
    address2 varchar(50),
    orderrequest varchar(50)
);

create table table_fav (
    md_code int,
    user_id varchar(30)
);


insert into table_user values ('admin','1234','010-1234-1234','집','집','abc@com.com',null,null,1);
