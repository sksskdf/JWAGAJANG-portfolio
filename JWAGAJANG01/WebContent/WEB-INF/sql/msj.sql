select * from table_order;
desc table_md;
desc table_order;

select * from table_user;

alter table table_md add md_ordercnt int;
alter table table_md modify md_ordercnt int default 0;

update table_md set md_ordercnt=0 where md_code=4;

select md_code, md_name, md_price, md_dc, img_main from table_md order by md_ordercnt desc limit 4;
select md_code, md_name, md_price, md_dc, img_main from table_md order by md_regdate  desc limit 4;

insert into `table_md` (`md_code`, `md_name`, `md_price`, `md_dc`, `md_ordercnt`, `img_main`, `img_detail`, `md_regdate`, `md_editdate`, `category_main`, `category_sub`) 
values(7, '유기농 딸기(500g*4개) 선물용', 62000, 20, 25, '/img/mdImg/md1.jpeg', '/img/mdimg/md1_detai...', now(), now(), '100', '170');
insert into `table_md` (`md_code`, `md_name`, `md_price`, `md_dc`, `md_ordercnt`, `img_main`, `img_detail`, `md_regdate`, `md_editdate`, `category_main`, `category_sub`) 
values(2, '숙주나물(무농약녹두콩, 300g)', 2400, 10, 21, '/img/mdImg/md2.jpg', '/img/mdimg/md2_detail.jpg', now(), now(), '100', '160');
insert into `table_md` (`md_code`, `md_name`, `md_price`, `md_dc`, `md_ordercnt`, `img_main`, `img_detail`, `md_regdate`, `md_editdate`, `category_main`, `category_sub`) 
values(6, '유기농토마토즙(120ml*20포)', 24500	, 0, 15, '/img/mdImg/md3.jpg', '/img/mdimg/md3_detail.jpg', now(), now(), '100', '170');
insert into `table_md` (`md_code`, `md_name`, `md_price`, `md_dc`, `md_ordercnt`, `img_main`, `img_detail`, `md_regdate`, `md_editdate`, `category_main`, `category_sub`) 
values(4, '생표고버섯(무농약,150g)', 2300	, 5, 20, '/img/mdImg/md4.jpg', '/img/mdimg/md4_detail.jpg', now(), now(), '100', '160');
insert into `table_md` (`md_code`, `md_name`, `md_price`, `md_dc`, `md_ordercnt`, `img_main`, `img_detail`, `md_regdate`, `md_editdate`, `category_main`, `category_sub`) 
values(5, '명품수제숯불떡갈비세트(3pcs)*3set', 3700, 10, 7, '/img/mdImg/md5.jpg', '/img/mdimg/md5_detail.jpg', now(), now(), '400', '410');

