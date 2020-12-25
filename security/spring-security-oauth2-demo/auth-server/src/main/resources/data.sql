
INSERT INTO user_accounts(id, email, password,name, role) VALUES
(1, 'admin@gmail.com', '$2a$10$Dtg53WwZQKElBGxc2.LbCO3Pcksiza4UlODNlx26HWKwKeiAPbMHi', 'Administrator', 'ROLE_ADMIN'),
(2, 'siva@gmail.com', '$2a$10$PVtQlBOYqz53qZ.HDSW0JOBAZLUDLkmdPZ.wMCPM2euydVX3dyVfa', 'Siva', 'ROLE_USER')
;

INSERT INTO oauth_client_details (
     client_id, resource_ids, client_secret, scope,
     authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity,
     refresh_token_validity, additional_information, autoapprove)
VALUES
('client1', null, '$2a$10$bBxQ.2i7vzNEzfzd54W5j.e94C7/82EOtjhtmgj3qj7lCCHi8W8AW','read_catalog,read_promos',
'authorization_code,implicit,password,client_credentials,refresh_token','http://localhost:8080/ui/',null, 3000, -1, null, 'false');