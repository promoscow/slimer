create table programs
(
    id               bigint auto_increment
        primary key,
    active           int          null,
    created          datetime     not null,
    updated          datetime     null,
    actual           bit          null,
    estimated_finish date         null,
    is_finished      bit          null,
    goal_weight      double       null,
    program_type     varchar(255) null,
    start_weight     double       null,
    user_id          bigint       not null
);

create index program_user_index
    on programs (user_id);