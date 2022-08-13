
INSERT INTO roles (id, name, created_at) VALUES
(1, 'ROLE_ADMIN', CURRENT_TIMESTAMP),
(2, 'ROLE_MODERATOR', CURRENT_TIMESTAMP),
(3, 'ROLE_USER', CURRENT_TIMESTAMP)
;

INSERT INTO users (email, password, name, user_type, created_at) VALUES
('sivaprasadreddy.k@gmail.com', '$2a$10$qW08MYCgzkYNoFFXYHUqluGGyLtBJK/XAQtu0lmsjD2mWaUiPQeZ2', 'K Siva Prasad Reddy', 'LOCAL', CURRENT_TIMESTAMP)
;

INSERT INTO user_role (user_id, role_id) VALUES
(1, 1),
(1, 2),
(1, 3)
;
