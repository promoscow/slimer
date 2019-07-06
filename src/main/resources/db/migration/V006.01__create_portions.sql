create table portions
(
    id         bigint auto_increment
        primary key,
    active     int      null,
    created    datetime not null,
    updated    datetime null,
    weight     int      null,
    meal_id    bigint   not null,
    product_id bigint   not null
);

create index portion_product_id
    on portions (product_id);

create index portion_meal_index
    on portions (meal_id);