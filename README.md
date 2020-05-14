# Log Manager

Java API RESTfull for Log managing. It allows you to upload log batch files, list and search for Log entries, and edit log entries. 

## TODO List
- [x] Decide Framework: Spring
- [x] Decide Database: Postegres
- [x] Decide Front: Angular
- [x] Bring VSCode environment up
- [x] Bring Database up with docker
- [x] API RESTFull Spring Boot
- [x] Handle Upload File
- [x] Import uploaded data
- [ ] Frontend Authentication (Login)
- [x] Frontend CRUD for file upload
- [ ] Add service logs for debugging propouses
- [x] Solve CORS problems (at front only)
- [x] Frontend CRUD for log management
- [ ] Backend validations
- [x] Frontend validations
- [ ] Unit tests
- [ ] Test coverage
- [ ] Terraform for CD/CI pipeline
- [x] Add list pagination
- [ ] Add backend token authentication

## Environment

Visual Studio Code with Maven as packager manager

> Reference: https://www.youtube.com/watch?v=dkmlOi_MNb4

## Usage

### List

```
Method: GET
http://localhost:8080/api/list
```

### Get
```
Method: GET
http://localhost:8080/api/get/1
```

### Add
```
Method: POST
http://localhost:8080/api/add
```

```
{
    "entryDate": "2020-01-01",
    "ipAddress": "192.168.234.82",
    "httpMethod": "GET / HTTP/1.1",
    "returnCode": 200,
    "browserDetail": "swcd (unknown version) CFNetwork/808.2.16 Darwin/15.6.0"
}
```

### Edit
```
Method: PUT
http://localhost:8080/api/edit
```

```
{
    "id":1,
    "entryDate": "2020-01-01",
    "ipAddress": "192.168.234.82",
    "httpMethod": "GET / HTTP/1.1",
    "returnCode": 200,
    "browserDetail": "swcd (unknown version) CFNetwork/808.2.16 Darwin/15.6.0"
}
```

## Database

```
$ docker-machine start
```
```
$ docker run --rm --name go-postgres -p 5432:5432 -e POSTGRES_PASSWORD=postgres postgres:latest
```
```
$ docker-machine env
```


