This project contains a service which writes to postgres  1 repository (table) with one entity.
The project uses latest springboot 2.7.2 (2022) and mapstruct
status: OK


## quick test
```sh 
mvn clean package && docker-compose build && ./test-it-all.bash start stop
```



## Testing

####  connecto to psg in docker
```sh
docker exec -it db  psql -h db -U compose-postgres
```

### inject entity 
when app is running in docker
```bash
curl -X POST http://localhost:8080/customer -H "Content-Type: application/json" --data '{"customerId": 1, "firstName":"John", "lastName":"Doe"}'
```
