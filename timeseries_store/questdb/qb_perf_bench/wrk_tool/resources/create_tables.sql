
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

