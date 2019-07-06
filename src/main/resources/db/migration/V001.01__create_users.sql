drop table if exists users;

create table users
(
    id           bigint auto_increment
        primary key,
    active       int          null,
    created      datetime     not null,
    updated      datetime     null,
    age          int          null,
    birth_date   date         null,
    bmi_category varchar(255) null,
    bmi          double       null,
    dci          double       not null,
    gender       int          null,
    height       int          null,
    pai          varchar(255) not null,
    weight       double       null
);