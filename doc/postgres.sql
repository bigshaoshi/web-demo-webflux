create table sys_user
(
    id       bigserial
        primary key,
    name     varchar(255) not null,
    username varchar(255) not null
        unique,
    password varchar(255) not null,
    email    varchar(50),
    deleted  integer default 0
);