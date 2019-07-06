create table workouts
(
    id          bigint auto_increment
        primary key,
    active      int      null,
    created     datetime not null,
    updated     datetime null,
    date        date     null,
    duration    int      null,
    activity_id bigint   not null,
    user_id     bigint   not null
);

create index workout_activity_index
    on workouts (activity_id);

create index workout_user_index
    on workouts (user_id);