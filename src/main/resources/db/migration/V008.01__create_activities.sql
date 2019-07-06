create table activities
(
    id       bigint auto_increment
        primary key,
    active   int          null,
    created  datetime     not null,
    updated  datetime     null,
    calories int          not null,
    name     varchar(255) not null,
    type     varchar(255) not null
);