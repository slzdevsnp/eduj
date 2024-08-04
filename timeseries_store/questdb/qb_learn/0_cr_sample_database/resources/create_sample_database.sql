

-- create trades table, it is a wal table (write ahead log)
-- partitioned by timestamp
-- NB trades table
CREATE TABLE trades (
    timestamp TIMESTAMP,
    symbol SYMBOL,
    side SYMBOL,
    price DOUBLE,
    amount DOUBLE
) TIMESTAMP(timestamp) PARTITION BY DAY WAL
DEDUP UPSERT KEYS(timestamp, symbol);


-- timestamp sequence generator understanding
-- sequence generator
SELECT x, timestamp_sequence(
            to_timestamp('2024-01-01T00:00:00', 'yyyy-MM-ddTHH:mm:ss'),
            1000000L)  --- 1M inc = 1M ms = 1s  incr
FROM long_sequence(10);

SELECT x, timestamp_sequence('2024-01-01T00:00:00', 60000000L)  --- 60M inc = 60 sec = 1 min incr
FROM long_sequence(10);

SELECT x, timestamp_sequence('2024-01-01T00:00:00', 1L * x)  --- increment grows and is based on x long value
                                                             -- the base is a current row computed timestamp
FROM long_sequence(10);


--- insert as select with rnd_   func
INSERT INTO trades
    SELECT
        timestamp_sequence('2024-01-01T00:00:00', 60000000L ) timestamp, -- Generate a timestamp every minute starting from Jan 1, 2024
        rnd_str('ETH-USD', 'BTC-USD', 'SOL-USD', 'LTC-USD', 'UNI-USD') symbol, -- Random ticker symbols
        rnd_str('buy', 'sell') side, -- Random side (BUY or SELL)
        rnd_double() * 1000 + 100 price, -- Random price between 100.0 and 1100.0,
        rnd_double() * 2000 + 0.1 amount -- Random price between 0.1 and 2000.1
    FROM long_sequence(10000) x;  -- inserting 10K rows


select count(*) from trades;
select * from trades limit 40;
select * from trades order by timestamp desc  limit 10

--TRUNCATE table trades;

--------------------------------------------
-------------- senosors and readings
--------------------------------------------


