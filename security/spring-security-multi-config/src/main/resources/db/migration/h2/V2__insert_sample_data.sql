
INSERT INTO roles (id, name, created_at) VALUES
(1, 'ROLE_ADMIN', CURRENT_TIMESTAMP()),
(2, 'ROLE_USER', CURRENT_TIMESTAMP())
;

INSERT INTO users (email, password, name, created_at) VALUES
('admin@gmail.com', '$2a$10$dPLX6R2rnU94KWmNokNILuxmxwkgPLM01/kbWWqb7ULIuG4qIJmpC', 'Admin',  CURRENT_TIMESTAMP()),
('siva@gmail.com', '$2a$10$Ib73a9lzoX60I3UIlwm.8OSfbM8WQDaggieVDjxb1zoC6W/tJAA62', 'Siva',  CURRENT_TIMESTAMP())
;

INSERT INTO user_role (user_id, role_id) VALUES
(1, 1),
(1, 2),
(2, 2)
;


INSERT INTO todos(content, created_by,created_at) VALUES
('Learn AWS',1,CURRENT_TIMESTAMP()),
('Learn SpringBoot',2,CURRENT_TIMESTAMP())
;
