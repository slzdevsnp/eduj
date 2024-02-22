

CREATE TABLE sensors (ID LONG, make STRING, city STRING);


-- inserts 10K records
INSERT INTO sensors
    SELECT
        x ID, --increasing integer
        rnd_str('Eberle', 'Honeywell', 'Omron', 'United Automation', 'RS Pro') make,
        rnd_str('New York', 'Miami', 'Boston', 'Chicago', 'San Francisco') city
    FROM long_sequence(10000) x;


select count(*) from sensors;

select * from sensors limit 10;

truncate table sensors;

drop table sensors;