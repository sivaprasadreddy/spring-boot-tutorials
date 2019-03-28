create sequence user_id_seq start with 10 increment by 50;
create sequence role_id_seq start with 10 increment by 50;
create sequence bm_id_seq start with 10 increment by 50;
create sequence txn_id_seq start with 10 increment by 50;
create sequence todo_id_seq start with 10 increment by 50;
create sequence note_id_seq start with 10 increment by 50;

create table users (
    id bigint DEFAULT nextval('user_id_seq') not null,
    email varchar(255) not null CONSTRAINT user_email_unique UNIQUE,
    password varchar(255) not null,
    name varchar(255) not null,
    enabled boolean not null,
    last_password_reset_date timestamp,
    created_on timestamp default current_timestamp,
    updated_on timestamp,
    primary key (id)
);

create table roles (
    id bigint DEFAULT nextval('role_id_seq') not null,
    name varchar(255) not null CONSTRAINT role_name_unique UNIQUE,
    created_on timestamp default current_timestamp,
    updated_on timestamp,
    primary key (id)
);

create table user_role (
    user_id bigint REFERENCES users(id),
    role_id bigint REFERENCES roles(id)
);

create table bookmarks (
    id bigint DEFAULT nextval('bm_id_seq') not null,
    title varchar(255) not null,
    url varchar(255) not null,
    created_by bigint REFERENCES users(id),
    created_on timestamp default current_timestamp,
    updated_on timestamp,
    primary key (id)
);

create table transactions (
    id bigint DEFAULT nextval('txn_id_seq') not null,
    amount decimal(12,2) not null,
    type varchar(50) not null,
    created_by bigint REFERENCES users(id),
    created_on timestamp default current_timestamp,
    updated_on timestamp,
    primary key (id)
);

create table todos (
    id bigint DEFAULT nextval('todo_id_seq') not null,
    text varchar(1024) not null,
    done boolean not null default false,
    created_by bigint REFERENCES users(id),
    created_on timestamp default current_timestamp,
    updated_on timestamp,
    primary key (id)
);

create table notes (
    id bigint DEFAULT nextval('note_id_seq') not null,
    text varchar(1024) not null,
    created_by bigint REFERENCES users(id),
    created_on timestamp default current_timestamp,
    updated_on timestamp,
    primary key (id)
);
