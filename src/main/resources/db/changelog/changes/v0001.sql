create table if not exists news
(
    id    bigserial
        constraint news_pk
            primary key,
    time  timestamp(6) not null,
    title varchar(200) not null,
    text  varchar      not null

);

alter table news
    owner to postgres;

create unique index if not exists news_id_uindex
    on news (id);

create table if not exists comment
(
    id       bigserial
        constraint comment_pk
            primary key,
    time     timestamp(6) not null,
    text     varchar(500) not null,
    username varchar(50)  not null,
    news_id  bigserial    not null
);

alter table comment
    owner to postgres;

create unique index if not exists comment_id_uindex
    on comment (id);


