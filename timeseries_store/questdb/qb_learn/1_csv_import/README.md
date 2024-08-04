url: https://questdb.io/docs/configuration/#csv-import

## default ports:

- 9000 - REST API and Web Console
- 9009 - InfluxDB line protocol
- 8812 - Postgres wire protocol
- 9003 - Min health server


## docker-compose has config to configure the copy
- 2 additional mount points  and variable `QDB_CAIRO_SQL_COPY_ROOT`

