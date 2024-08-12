#!/usr/bin/env bash


## insert some additional data

curl -G \
  --data-urlencode "query=INSERT INTO trades VALUES('abc', 123456)" \
  http://localhost:9000/exec

ts_id_val=3 ; base_val=50.0
qr="INSERT INTO  tserie_ordinary SELECT ${ts_id_val} ts_id, timestamp_sequence('2023-01-01T00:00:00', 15*60000000L ) timestamp, rnd_double() * 1000 + ${base_val} value FROM long_sequence(40000) x;"

curl -G --data-urlencode "query=${qr}" http://localhost:9000/exec

# yet another ts_id
ts_id_val=4 base_val=150.0
qr="INSERT INTO  tserie_ordinary SELECT ${ts_id_val} ts_id, timestamp_sequence('2023-01-01T00:00:00', 15*60000000L ) timestamp, rnd_double() * 1000 + ${base_val} value FROM long_sequence(40000) x;"

curl -G --data-urlencode "query=${qr}" http://localhost:9000/exec

s_query="select  * from tserie_ordinary where ts_id = 1 and timestamp between '2023-12-15T00:00:00' and '2023-12-31T23:45:00';"

encoded_q="select%20%20%2A%20from%20tserie_ordinary%20where%20ts_id%20%3D%201%20and%20timestamp%20between%20%272023-12-15T00%3A00%3A00%27%20and%20%272023-12-31T23%3A45%3A00%27%3B"


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




#### mimicking  orderbook
##1
q="select * FROM  orderbook  where tso = 'AMP' and timestamp between '2023-01-01T01:00:00' and '2023-01-01T03:00:00';"

enq="select%20%20%2A%20FROM%20%20orderbook%20%20%20where%20tso%20%3D%20%27AMP%27%20and%20timestamp%20between%20%272023-01-01T01%3A00%3A00%27%20and%20%272023-01-01T03%3A00%3A00%27%3B"

## pulls 1.4 M records
fqurl="http://localhost:9000/exec?query=select%20%20%2A%20FROM%20%20orderbook%20%20%20where%20tso%20%3D%20%27AMP%27%20and%20timestamp%20between%20%272023-01-01T01%3A00%3A00%27%20and%20%272023-01-01T03%3A00%3A00%27%3B&count=true"

curl $fqurl


wrk -t10 -c20 -d20s $fqurl

#  10 threads and 20 connections
#  Thread Stats   Avg      Stdev     Max   +/- Stdev
#    Latency     0.00us    0.00us   0.00us    -nan%
#    Req/Sec     0.90      2.19    10.00     95.24%
#  21 requests in 10.01s, 4.03GB read
#  Socket errors: connect 0, read 0, write 0, timeout 21
# Requests/sec:      2.10
# Transfer/sec:    412.17MB


wrk -t10 -c20 -d20s $fqurl
#Running 1m test @ http://localhost:9000/exec?query=select%20%20%2A%20FROM%20%20orderbook%20%20%20where%20tso%20%3D%20%27AMP%27%20and%20timestamp%20between%20%272023-01-01T01%3A00%3A00%27%20and%20%272023-01-01T03%3A00%3A00%27%3B&count=true
#  10 threads and 20 connections
#  Thread Stats   Avg      Stdev     Max   +/- Stdev
#    Latency     0.00us    0.00us   0.00us    -nan%
#    Req/Sec     0.70      1.97    10.00     93.14%
#  206 requests in 1.00m, 24.18GB read
#  Socket errors: connect 0, read 0, write 0, timeout 206
#Requests/sec:      3.43
#Transfer/sec:    412.01MB

##2
q="select * FROM orderbook where tso = 'AMP' and contract='00H' and timestamp between '2023-01-01T01:00:00' and '2023-01-01T23:59:59.999';"

enq="select%20%2A%20FROM%20orderbook%20where%20tso%20%3D%20%27AMP%27%20and%20contract%3D%2700H%27%20and%20timestamp%20between%20%272023-01-01T01%3A00%3A00%27%20and%20%272023-01-01T23%3A59%3A59.999%27%3B"

fqurl="http://localhost:9000/exec?query=select%20%2A%20FROM%20orderbook%20where%20tso%20%3D%20%27AMP%27%20and%20contract%3D%2700H%27%20and%20timestamp%20between%20%272023-01-01T01%3A00%3A00%27%20and%20%272023-01-01T23%3A59%3A59.999%27%3B"

curl $fqurl

wrk -t10 -c20 -d20s $fqurl
#Running 20s test @ http://localhost:9000/exec?query=select%20%2A%20FROM%20orderbook%20where%20tso%20%3D%20%27AMP%27%20and%20contract%3D%2700H%27%20and%20timestamp%20between%20%272023-01-01T01%3A00%3A00%27%20and%20%272023-01-01T23%3A59%3A59.999%27%3B
#  10 threads and 20 connections
#  Thread Stats   Avg      Stdev     Max   +/- Stdev
#    Latency     0.00us    0.00us   0.00us    -nan%
#    Req/Sec     0.65      2.40    10.00     94.12%
#  34 requests in 20.04s, 2.25GB read
#  Socket errors: connect 0, read 0, write 0, timeout 34
#Requests/sec:      1.70
#Transfer/sec:    114.77MB
