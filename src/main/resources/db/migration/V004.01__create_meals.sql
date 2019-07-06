create table meals
(
    id      bigint auto_increment
        primary key,
    active  int          null,
    created datetime     not null,
    updated datetime     null,
    date    date         null,
    name    varchar(255) null,
    user_id bigint       not null
);

create index meal_user_index
    on meals (user_id);