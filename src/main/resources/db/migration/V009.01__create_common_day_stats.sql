create table common_day_stats
(
    id                bigint auto_increment
        primary key,
    active            int      null,
    created           datetime not null,
    updated           datetime null,
    calories_burn     bigint   null,
    calories_consumed bigint   null,
    date              date     not null,
    is_goal_reached   bit      null,
    user_id           bigint   not null,
    constraint common_day_stats_user_id_date_constraint
        unique (user_id, date)
);

create index common_day_stats_user_index
    on common_day_stats (user_id);