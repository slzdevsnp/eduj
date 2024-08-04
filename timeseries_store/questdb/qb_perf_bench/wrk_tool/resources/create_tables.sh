#!/usr/bin/env bash


## insert some additional data

curl -G \
  --data-urlencode "query=INSERT INTO trades VALUES('abc', 123456)" \
  http://localhost:9000/exec

ts_id_val=3
base_val=50.0
qr="INSERT INTO  tserie_ordinary SELECT ${ts_id_val} ts_id, timestamp_sequence('2023-01-01T00:00:00', 15*60000000L ) timestamp, rnd_double() * 1000 + ${base_val} value FROM long_sequence(40000) x;"

curl -G --data-urlencode "query=${qr}" http://localhost:9000/exec

# yet another ts_id
ts_id_val=4 base_val=150.0
qr="INSERT INTO  tserie_ordinary SELECT ${ts_id_val} ts_id, timestamp_sequence('2023-01-01T00:00:00', 15*60000000L ) timestamp, rnd_double() * 1000 + ${base_val} value FROM long_sequence(40000) x;"

curl -G --data-urlencode "query=${qr}" http://localhost:9000/exec

s_query="select  * from tserie_ordinary where ts_id = 1 and timestamp between '2023-12-15T00:00:00' and '2023-12-31T23:45:00';"

encoded_q="select%20%20%2A%20from%20tserie_ordinary%20where%20ts_id%20%3D%201%20and%20timestamp%20between%20%272023-12-15T00%3A00%3A00%27%20and%20%272023-12-31T23%3A45%3A00%27%3B"

fqurl="http://localhost:9000/exec?query

fqurl="http://localhost:9000/exec?query=select%20%20%2A%20from%20tserie_ordinary%20where%20ts_id%20%3D%201%20and%20timestamp%20between%20%272023-12-15T00%3A00%3A00%27%20and%20%272023-12-31T23%3A45%3A00%27%3B&count=true"

## checking full url with params
curl $fqurl

## stress testing with wrk

wrk -t10 -c20 -d10s $fqurl

#10 threads and 20 connections
#  Thread Stats   Avg      Stdev     Max   +/- Stdev
#    Latency    21.40ms   21.31ms  99.29ms   78.50%
#    Req/Sec   132.16     29.13   230.00     73.30%
#  13261 requests in 10.07s, 1.04GB read
#Requests/sec:   1316.55
#Transfer/sec:    105.31MB



