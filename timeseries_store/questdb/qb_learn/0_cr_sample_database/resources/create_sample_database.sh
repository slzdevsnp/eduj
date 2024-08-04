#!/usr/bin/env bash

## get 1 day of data  from   trades table

url="http://localhost:9000/exec"

curl -G data-urlencode "query=select * from trades  where  timestamp between '2024-01-02T00:00:00' and '2024-01-03T00:00:00';" data-urlencode "count=true"  http://localhost:9000/exec

## query non-encoded
curl -X GET "http://localhost:9000/exec?query=select * from trades  where  timestamp between '2024-01-02T00:00:00' and '2024-01-03T00:00:00';&count=true"
#working
url="http://localhost:9000/exec?query=select%20%2A%20from%20trades%20%20where%20%20timestamp%20between%20%272024-01-02T00%3A00%3A00%27%20and%20%272024-01-03T00%3A00%3A00%27%3B&count=true"
curl -X GET $url
## ab

### stress testing with wrk  10 threads, 10 conns  over 10 secs on trades table (10 K records)
wrk -t10 -c10 -d10s $url
## result 12903 requests in 10.04s, 1.44GB read
#Requests/sec:   1285.72

wrk -t10 -c10 -d120s $url
#160647 requests in 2.00m, 17.97GB read
#Requests/sec:   1337.60
#Transfer/sec:    153.24MB