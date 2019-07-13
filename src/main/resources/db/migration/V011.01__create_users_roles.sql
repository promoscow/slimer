create table users_roles
(
    user_id bigint not null,
    role_id bigint not null
);

alter table users_roles
    add constraint users_roles_roles_id_fk
        foreign key (role_id) references roles (id);

alter table users_roles
    add constraint users_roles_users_id_fk
        foreign key (user_id) references users (id);