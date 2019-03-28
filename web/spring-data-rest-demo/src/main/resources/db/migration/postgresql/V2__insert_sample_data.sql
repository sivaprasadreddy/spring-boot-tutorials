
INSERT INTO USERS (id, username, password, name, enabled, last_password_reset_date) VALUES
(1, 'admin@gmail.com', '$2a$10$zuI3P8hoZNkFGR2dDPW9juA1C1xIHBUNrKMGqjjaEKsLTwjJkKoNa', 'Admin', true, null),
(2, 'siva@gmail.com', '$2a$10$LskLrNP6m.dEpXYjT41lRePseXJEjhd6.sPH2Z5GbbShtaFRWoeYq', 'Siva', true, null);

INSERT INTO ROLES (id, name) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER')
;

INSERT INTO USER_ROLE (user_id, role_id) VALUES
(1, 1),
(1, 2),
(2, 2);


INSERT INTO BOOKMARKS(id, title, url, created_by, created_on) VALUES
(1, 'MicroServices – Part 6 : Distributed Tracing with Spring Cloud Sleuth and Zipkin','https://sivalabs.in/2018/03/microservices-part-6-distributed-tracing-with-spring-cloud-sleuth-and-zipkin/',1,now()),
(2, 'MicroServices – Part 5 : Spring Cloud Zuul Proxy as API Gateway','https://sivalabs.in/2018/03/microservices-part-5-spring-cloud-zuul-proxy-as-api-gateway/',2,now()),
(3, 'MicroServices – Part 4 : Spring Cloud Circuit Breaker using Netflix Hystrix','https://sivalabs.in/2018/03/spring-cloud-netflix-circuit-breaker/',2,now())
;
