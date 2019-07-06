create table products
(
    id            bigint auto_increment
        primary key,
    active        int          null,
    created       datetime     not null,
    updated       datetime     null,
    calories      int          null,
    carbohydrates double       null,
    fats          double       null,
    name          varchar(255) null,
    proteins      double       null
);