create sequence user_id_seq start with 1 increment by 1;
create sequence role_id_seq start with 1 increment by 1;
create sequence bm_id_seq start with 1 increment by 1;

create table users (
    id bigint DEFAULT nextval('user_id_seq') not null,
    email varchar(255) not null,
    password varchar(255) not null,
    name varchar(255) not null,
    image_url varchar(255),
    user_type varchar(50) not null,
    created_at timestamp,
    updated_at timestamp,
    primary key (id),
    CONSTRAINT user_email_unique UNIQUE(email)
);

create table roles (
    id bigint DEFAULT nextval('role_id_seq') not null,
    name varchar(255) not null,
    created_at timestamp,
    updated_at timestamp,
    primary key (id),
    CONSTRAINT role_name_unique UNIQUE(name)
);

create table user_role (
    user_id bigint not null REFERENCES users(id),
    role_id bigint not null REFERENCES roles(id)
);

create table bookmarks (
    id bigint DEFAULT nextval('bm_id_seq') not null,
    url varchar(1024) not null,
    title varchar(1024),
    created_by bigint not null REFERENCES users(id),
    created_at timestamp,
    updated_at timestamp,
    primary key (id)
);
