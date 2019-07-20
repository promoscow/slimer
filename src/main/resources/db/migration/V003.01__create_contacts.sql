create table contacts
(
    id         bigint auto_increment
        primary key,
    active     int          null,
    created    datetime     not null,
    updated    datetime     null,
    email      varchar(255) null,
    phone      bigint       null,
    user_id    bigint       null,
    is_default bit          null
);

create index contact_user_index
    on contacts (user_id);