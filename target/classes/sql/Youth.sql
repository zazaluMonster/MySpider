/*中国青年网的表sql*/

-- 创建新闻表
create table YOUTH_NEWS
(
    id           int         not null,
    release_time TIMESTAMP   not null,
    title        VARCHAR(50) not null,
    crawl_time   TIMESTAMP   not null,
    constraint YOUTH_NEWS_pk
        primary key (id)
) comment '新闻表';

alter table YOUTH_NEWS modify id int auto_increment;


