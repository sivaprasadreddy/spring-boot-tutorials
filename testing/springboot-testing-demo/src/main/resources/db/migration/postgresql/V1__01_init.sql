create sequence user_id_seq start with 1 increment by 1;

create table users (
    id bigint DEFAULT nextval('user_id_seq') not null,
    email varchar(100) not null UNIQUE,
    password varchar(100) not null,
    name varchar(100) not null,
    primary key (id)
);
