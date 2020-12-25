insert into privileges(id, name) values
(1, 'CREATE_POST'),
(2, 'MODERATE_POST'),
(3, 'DELETE_POST')
;

insert into roles(id, name) values
(1, 'ROLE_USER'),
(2, 'ROLE_MODERATOR'),
(3, 'ROLE_ADMIN')
;

insert into users(id, email, password) values
(1, 'siva', 'siva'),
(2, 'prasad', 'prasad'),
(3, 'admin', 'admin')
;

insert into role_privilege(role_id, privilege_id) values
(1, 1),
(2, 1),
(2, 2),
(3, 1),
(3, 2),
(3, 3)
;

insert into user_role(user_id, role_id) values
(1, 1),
(2, 2),
(3, 3)
;