
------ hakom mimicking

CREATE TABLE tserie_ordinary (
    ts_id long,
    timestamp TIMESTAMP,
    value DOUBLE
) TIMESTAMP(timestamp) PARTITION BY WEEK WAL
DEDUP UPSERT KEYS(timestamp, ts_id);

CREATE TABLE tserie_versioned (
    ts_id long,
    version_ts TIMESTAMP,
    timestamp TIMESTAMP,
    value DOUBLE
) TIMESTAMP(timestamp) PARTITION BY WEEK WAL
DEDUP UPSERT KEYS(timestamp, version_ts, ts_id);



--insert data in tseries_ordinary   1 year of 15 mins data
-- OK
INSERT INTO  tserie_ordinary
SELECT
    1 ts_id,
    timestamp_sequence('2023-01-01T00:00:00', 15*60000000L ) timestamp,
    rnd_double() * 1000 + 100 value
FROM long_sequence(40000) x;

-- 2nd serie
INSERT INTO  tserie_ordinary
SELECT
    2 ts_id,
    timestamp_sequence('2023-01-01T00:00:00', 15*60000000L ) timestamp,
    rnd_double() * 1000 + 200 value
FROM long_sequence(40000) x;


-- ok to query 2 weeks of  15 mins data of a particular serie

select  * from tserie_ordinary where ts_id = 1 and timestamp between '2023-12-15T00:00:00' and '2023-12-31T23:45:00';



----  volue/likron market data mimicking

CREATE TABLE orderbook (tso varchar,
                        timestamp TIMESTAMP,
                        ask_p double,
                        ask_q int,
                        bid_p double,
                        bid_q int,
                        contract varchar
) TIMESTAMP(timestamp) PARTITION BY DAY WAL
DEDUP UPSERT KEYS(timestamp);


--insertion
--- insert 100 M records 1 at 1ms  this covers
INSERT INTO orderbook
SELECT
    rnd_varchar('AMP', 'RTE', 'ESS', 'CHE', 'AUT' ) tso,
    timestamp_sequence('2023-01-01T00:00:00', 1000L ) timestamp,
        rnd_double() * 1000 + 100 ask_p,
        rnd_int(1,10,0) ask_q,
        -1*(rnd_double() * 1000 + 100) bid_p,
        rnd_int(1,10,0) bid_q,
        rnd_varchar('00H', '01H', '02H', '03H', '04H', '05H', '06H', '07H', '08H', '09H', '10H', '11H',
 '12H', '13H', '14H', '15H', '16H', '17H', '18H', '19H', '20H', '21H', '22H', '23H') contract
FROM long_sequence(100*1000000L) ; -- 100 millions


select
--count(1)
*
FROM  orderbook
where tso = 'AMP' and timestamp between '2023-01-01T01:00:00' and '2023-01-01T03:00:00'; --1.4 M recs


select * from orderbook limit 10;

select * from orderbook order by timestamp desc limit 10;

select  count(*) from orderbook;


truncate table orderbook;

