create table registration_tokens
(
    id              bigint auto_increment
        primary key,
    active          int          null,
    created         datetime     not null,
    updated         datetime     null,
    user_id         bigint       null,
    token           varchar(255) not null,
    expiration_time datetime     not null
);

create index registration_token_token_index
    on registration_tokens (token)