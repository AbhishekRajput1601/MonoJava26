# StudentManager

Student CRUD API built with Spring Boot.

## Endpoints

- `GET /students` - list all students
- `GET /students/{id}` - get a student by id
- `POST /students` - create a student
- `PUT /students/{id}` - update a student
- `DELETE /students/{id}` - delete a student

## Student fields

- `s_id` - auto-generated primary key
- `s_name` - required, 2 to 100 characters
- `s_age` - required, 1 to 120
- `s_department` - required, 2 to 100 characters

