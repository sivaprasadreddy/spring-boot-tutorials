create sequence user_id_seq start with 10 increment by 50;
create sequence role_id_seq start with 10 increment by 50;
create sequence bm_id_seq start with 10 increment by 50;
create sequence txn_id_seq start with 10 increment by 50;
create sequence todo_id_seq start with 10 increment by 50;
create sequence note_id_seq start with 10 increment by 50;

create table users (
    id bigint default user_id_seq.nextval,
    email varchar(255) not null,
    password varchar(255) not null,
    name varchar(255) not null,
    enabled boolean not null,
    last_password_reset_date timestamp,
    created_on timestamp default current_timestamp,
    updated_on timestamp,
    primary key (id),
    UNIQUE KEY user_email_unique (email)
);

create table roles (
    id bigint default role_id_seq.nextval,
    name varchar(255) not null,
    created_on timestamp default current_timestamp,
    updated_on timestamp,
    primary key (id),
    UNIQUE KEY role_name_unique (name)
);

create table user_role (
    user_id bigint REFERENCES users(id),
    role_id bigint REFERENCES roles(id)
);

create table bookmarks (
    id bigint default bm_id_seq.nextval,
    title varchar(255) not null,
    url varchar(255) not null,
    created_by bigint REFERENCES users(id),
    created_on timestamp default current_timestamp,
    updated_on timestamp,
    primary key (id)
);

create table transactions (
    id bigint default txn_id_seq.nextval,
    amount decimal(12,2) not null,
    type varchar2(50) not null,
    created_on timestamp default current_timestamp,
    updated_on timestamp,
    created_by bigint REFERENCES users(id),
    primary key (id)
);

create table todos (
    id bigint default todo_id_seq.nextval,
    text varchar(1024) not null,
    done boolean not null default false,
    created_by bigint REFERENCES users(id),
    created_on timestamp default current_timestamp,
    updated_on timestamp,
    primary key (id)
);

create table notes (
    id bigint default note_id_seq.nextval,
    text varchar(1024) not null,
    created_by bigint REFERENCES users(id),
    created_on timestamp default current_timestamp,
    updated_on timestamp,
    primary key (id)
);
