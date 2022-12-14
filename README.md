# ๐ ๊ธ์ต์ํ ์ถ์ฒ ์๋น์ค

https://mango-tower-9f1.notion.site/7670e6d5a49d489f806ea2fb271d4fcb

1. [Development](#development)
2. [Deployment](#deployment )
3. [Dependency](#dependency )
4. [API specification](#api-specification)
5. [DB](#db)
6. [Result](#result)

<br>

## ๐ฆ Back-End Team

>ํ์ฅ: **๊น์ค๊ฒธ(_yoongyum_)**

>ํ์: **๊นํ์ค(khjun723), ์ฅํธ์ค(ho-jun97), ๊น์ฑ์ค(Meringue-KIm)**

>ํ๋ก ํธ ํ Repo: https://github.com/davidktlee/FC-ToyProject-Team3

<br>

## Development

- **IntelliJ**

- **openJDK** `11.0.10`

- **Spring boot** `2.7.3`

<br>

## Deployment

- **EC2 Instance** `free tier`
    - **ubuntu** `22.04 LTS`
    - **Elastic IP**
- **CloudFront**
- **Amazon Certificate Manager**(ACM)
- **Route 53**
    - `www.jaksim3.com`
    - `api.jaksim3.com`
- **Docker** `20.10.17`
    - **MySQL** `8.0.22`
    - **Docker Hub** - _yoongyum/spring-cicd_
        - https://hub.docker.com/?code=9uBU6bwy9K7BwwqZwqRnkaDEJvEnKOTlrLTbk-d7pWiMP&state=OFc2R21VY2NXUV9qYUxEVVkuT2IuZlRWRDZ5NloyMVl0NXlyblNGc0Nkdg%3D%3D

### `Server Deployment Structure`

![](https://velog.velcdn.com/images/yoongyum/post/e246b6e4-768a-4d6c-9240-2cdd6b8f8f8d/image.png)

<br>

## Dependency

- ***Spring Web***
- ***Spring DevTools***
- ***Spring Data JPA***
- ***Spring Security***
- ***Lombok***
- ***JWT***
- ***Swagger***
- ***MySQL Connector***

<br>

## API specification

https://www.jaksim3.com/swagger-ui.html

> **/auth/login** ๋ก๊ทธ์ธ ์์ฒญ `POST`
>
>**/auth/signup** ํ์ ๊ฐ์ `POST`

> **/member** ๋ด ์ ๋ณด `GET`

> **/products** ๊ธ์ต์ํ ์ ์ฒด ๋ชฉ๋ก `GET`
>
>**/products/customization** ๋ง์ถค ๊ธ์ต์ํ ๋ชฉ๋ก `GET`
>
>**/products/search** ๊ธ์ต์ํ ๊ฒ์ `POST`

> **/carts** ์ฅ๋ฐ๊ตฌ๋ ๋ชฉ๋ก `GET`
>
>**/carts** ์ฅ๋ฐ๊ตฌ๋ ๋น์ฐ๊ธฐ `DELETE`
>
>**/carts/{productId}** ์ฅ๋ฐ๊ตฌ๋ ๋ด๊ธฐ `POST`
>
>**/carts/{productId}** ์ฅ๋ฐ๊ตฌ๋์์ ๋นผ๊ธฐ `DELETE`

>**/interests** ๊ด์ฌ์ํ ๋ชฉ๋ก `GET`
>
>**/interests** ๊ด์ฌ์ํ ๋น์ฐ๊ธฐ `DELETE`
> 
> **/interests/{productId}** ๊ด์ฌ์ํ ์ถ๊ฐ `POST`
> 
> **/interests/{productId}** ๊ด์ฌ์ํ ์ญ์  `DELETE`

> **/orders/{productId}** ๊ธ์ต์ํ ์ฃผ๋ฌธ ์ ์ฒญ `POST`


<br>

## DB

<details>
<summary>DDL</summary>
<div markdown="1">

```mysql
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
```

</div>
</details>

![img.png](img/img.png)

<br>

## Result

ํ๋ก ํธ ํ์์ ๋ฐฐํฌํด์ฃผ์  Application

https://vermillion-shortbread-f46c4f.netlify.app/

![img_1.png](img/img_1.png)![img_2.png](img/img_2.png)![img_3.png](img/img_3.png)


### ์ญํ 

- ๐**๊น์ค๊ฒธ**
  - Spring Security
  - ์ฃผ๋ฌธ ์ ์ฒญ ๋ก์ง
  - ์๋ฒ ๋ฐฐํฌ 
  - CI/CD
- ๐งธ**๊นํ์ค**
  - Swagger
  - ์ํ ์ถ์ฒ, ๊ฒ์
- ๐**์ฅํธ์ค**
  - jwt
  - ๊ด์ฌ ๋ฑ๋ก, ์ญ์ , ๋ชฉ๋ก, ์ ์ฒด์ญ์ 
  - ์ฅ๋ฐ๊ตฌ๋ ๋ฑ๋ก, ์ญ์ , ๋ชฉ๋ก, ์ ์ฒด์ญ์ 
