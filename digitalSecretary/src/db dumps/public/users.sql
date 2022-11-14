create table users
(
    id       integer,
    login    varchar,
    password varchar
);

alter table users
    owner to postgres;

