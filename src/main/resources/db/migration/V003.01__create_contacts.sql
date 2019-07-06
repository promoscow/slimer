create table contacts
(
    id      bigint auto_increment
        primary key,
    active  int          null,
    created datetime     not null,
    updated datetime     null,
    email   varchar(255) null,
    phone   varchar(255) null,
    user_id bigint       not null
);

create index contact_user_index
    on contacts (user_id);