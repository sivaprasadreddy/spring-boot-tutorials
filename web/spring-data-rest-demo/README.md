# eBuddy REST API

### Authenticate User

`curl -H "Content-Type: application/json" -X POST -d '{"username":"admin@gmail.com","password":"admin"}' http://localhost:8080/api/auth/login`

`curl -H "Content-Type: application/json" -X POST -d '{"username":"siva@gmail.com","password":"siva"}' http://localhost:8080/api/auth/login`


### API Endpoints

`curl -H "Authorization: Bearer TOKEN_HERE" -X GET http://localhost:8080/api/me`

`curl -H "Authorization: Bearer TOKEN_HERE" -X GET http://localhost:8080/api/bookmarks`

`curl -H "Authorization: Bearer TOKEN_HERE" -X GET http://localhost:8080/api/todos`

`curl -H "Authorization: Bearer TOKEN_HERE" -X GET http://localhost:8080/api/notes`
