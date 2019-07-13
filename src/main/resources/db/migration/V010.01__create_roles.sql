create table roles
(
    id      bigint auto_increment
        primary key,
    active  int          null,
    created datetime     not null,
    updated datetime     null,
    role    varchar(255) not null
);