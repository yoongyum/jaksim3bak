drop table if exists cart_product cascade;
drop table if exists cart cascade;
drop table if exists interested_product cascade;
drop table if exists interested cascade;
drop table if exists order_product cascade;
drop table if exists product cascade;
drop table if exists member cascade;

create table member
(
    member_id      bigint auto_increment
        primary key,
    created_date   datetime(6)  null,
    modified_date  datetime(6)  null,
    age            int          not null,
    authority      varchar(255) null,
    available_loan bigint       not null,
    email          varchar(255) not null,
    job            varchar(255) not null,
    password       varchar(255) not null,
    username       varchar(255) not null,
    constraint UK_mbmcqelty0fbrvxp1q58dn57t
        unique (email)
);

create table product
(
    product_id  bigint auto_increment
        primary key,
    age         int          not null,
    institution varchar(50)  not null,
    job         varchar(255) null,
    loan        bigint       not null,
    logo        varchar(255) null,
    name        varchar(50)  not null
);

create table order_product
(
    order_product_id bigint auto_increment
        primary key,
    created_date     datetime(6) null,
    modified_date    datetime(6) null,
    member_id        bigint      null,
    product_id       bigint      null,
    constraint FKhnfgqyjx3i80qoymrssls3kno
        foreign key (product_id) references product (product_id),
    constraint FKk01qiu5b3r035sni65tmgph8d
        foreign key (member_id) references member (member_id)
);

create table cart
(
    id        bigint auto_increment
        primary key,
    member_id bigint null,
    constraint FKix170nytunweovf2v9137mx2o
        foreign key (member_id) references member (member_id)
);

create table cart_product
(
    id            bigint auto_increment
        primary key,
    created_date  datetime(6) null,
    modified_date datetime(6) null,
    cart_id       bigint      null,
    product_id    bigint      null,
    constraint FK2kdlr8hs2bwl14u8oop49vrxi
        foreign key (product_id) references product (product_id),
    constraint FKlv5x4iresnv4xspvomrwd8ej9
        foreign key (cart_id) references cart (id)
);

create table interested
(
    id        bigint auto_increment
        primary key,
    member_id bigint null,
    constraint FKi3swcjel7esh5q91xvni6an6b
        foreign key (member_id) references member (member_id)
);

create table interested_product
(
    id            bigint auto_increment
        primary key,
    created_date  datetime(6) null,
    modified_date datetime(6) null,
    interested_id bigint      null,
    product_id    bigint      null,
    constraint FK6g3jxf32xrx4jbs2wkka5vx1a
        foreign key (product_id) references product (product_id),
    constraint FK7pkibgkr5t2rndjyhxg7yyyq2
        foreign key (interested_id) references interested (id)
);



