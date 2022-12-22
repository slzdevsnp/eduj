# Lenses Poc
folder: /home/zimine/repos/clearn/stream/kafka/lenses-poc

## lenses box
docker container md:
```
docker run -e ADV_HOST=127.0.0.1  -v /home/zimine/repos/clearn/stream/kafka/lenses-poc/lic/license.json:/license.json --rm -p 3030:3030 -p 9092:9092  lensesio/box:latest
```

default user

queries

```
SELECT  *
from dip2.raw.wattsight limit 4

select * from (
SELECT regexp(payload, 'id":[0-9]+,') as rr FROM dip2.raw.wattsight  limit  100
)


## headers
SELECT  HEADERKEYS() as headers
from dip2.raw.wattsight LIMIT 2


SELECT  
_meta.timestamp as meta_t,
HEADERASSTRING('INGEST_TIMESTAMP') as h_ingest_ts,
HEADERASSTRING('MESSAGE_ID') as msg_id,
HEADERASSTRING('FLOW_ID') as flow_id,
HEADERASSTRING('INGESTION_IDENTIFIER') as ingestId,
HEADERASSTRING('INGEST_DATA_SET_NAME') as dsname

from dip2.raw.wattsight LIMIT 10



select 
HEADERASSTRING('INGESTION_IDENTIFIER') as ing_id,
count(1) as msg_per_ing_cnt from dip2.raw.wattsight 
group by HEADERASSTRING('INGESTION_IDENTIFIER')



```


```
SELECT count(1) as std_cnt
FROM dip2.structured.standard.wattsight 
where dHeader.ingestionIdentifier = 'ING_434baa4d52554536b73ce2950068575b'


--wt msg counts per ingestion_identifier
select 
dHeader.dataInterfaceIdentifier as std_intf_id,
dHeader.ingestionIdentifier as std_ing_id,
dHeader.dataSetName as dsname,
count(1) as msg_cnt
FROM dip2.structured.standard.wattsight
group by dHeader.ingestionIdentifier, dHeader.dataSetName, dHeader.dataInterfaceIdentifier

--emsys
select 
dHeader.dataInterfaceIdentifier as std_intf_id,
dHeader.ingestionIdentifier as std_ing_id,
dHeader.dataSetName as dsname,
count(1) as msg_cnt
FROM dip2.structured.standard.emsys.timeseries
group by dHeader.ingestionIdentifier, dHeader.dataSetName, dHeader.dataInterfaceIdentifier

```

theme
```
SELECT count(1) as theme_cnt
FROM dip2.structured.theme.timeseries 
where dHeader.ingestionIdentifier = 'ING_434baa4d52554536b73ce2950068575b'


--msg cnt for wt+em
select 
dHeader.dataInterfaceIdentifier as std_intf_id,
dHeader.ingestionIdentifier as std_ing_id,
dHeader.dataSetName as dsname,
count(1) as msg_cnt
FROM dip2.structured.theme.timeseries
group by dHeader.ingestionIdentifier, dHeader.dataSetName, dHeader.dataInterfaceIdentifier


```


hakom
```
SELECT 
count(1) as hakom_cnt
FROM dip2.subject.hakom.timeseries
where ingestionIdentifier='ING_434baa4d52554536b73ce2950068575b'


SELECT
ingestionIdentifier as ing_id,
name as datasetname,
count(1) as msg_cnt

FROM dip2.subject.hakom.timeseries 
where name like 'bulk_em%'
group by ingestionIdentifier, name


```


complex
```
select count(1) as cnt_raw
from dip2.raw.wattsight
WHERE 
HEADERASSTRING('INGESTION_IDENTIFIER')  = 'ING_9b1f4f26a9ce405cac227ae4d2b3ce99'
UNION ALL
SELECT count(1) as std_cnt
FROM dip2.structured.standard.wattsight 
where dHeader.ingestionIdentifier = 'ING_9b1f4f26a9ce405cac227ae4d2b3ce99'


--join takes time

SELECT
count(t1.*) as std_cnt,
count(t2.*) as theme_cnt
FROM dip2.structured.standard.wattsight t1
JOIN dip2.structured.theme.timeseries t2
on t1.dHeader.ingestionIdentifier = t2.dHeader.ingestionIdentifier
where t1.dHeader.ingestionIdentifier = 'ING_9b1f4f26a9ce405cac227ae4d2b3ce99'

```


tutorial

```

CREATE TABLE greetings(_key string, _value string) FORMAT (string, string);


INSERT INTO greetings(_key, _value) VALUES("Hello", "World");
INSERT INTO greetings(_key, _value) VALUES("Bonjour", "Monde");

SELECT * FROM greetings;

DROP TABLE greetings;
```


--metadata
```
CREATE TABLE tutorial(_key string, name string, difficulty int) FORMAT (Avro, Avro);

-- And then
INSERT INTO tutorial(_key, name, difficulty)
VALUES
("1", "Learn Lenses SQL", 3),
("2", "Learn Quantum Physics", 10),
("3", "Learn French", 5);

--for metadata
SELECT name, _meta.offset, _meta.timestamp, _meta.partition, _meta.__keysize, _meta.__valsize
FROM tutorial
```

-- simple arithmetics
```
-- Run
CREATE TABLE groceries (_key int, id int, name string, quantity int, price double) FORMAT(INT, Avro);

-- And then
INSERT INTO groceries(_key, id, name, quantity , price)
VALUES
(1, 1, "Fairtrade Bananas", 1, 1.90),
(2, 2, "Meridian Crunchy Peanut Butter", 1, 2.50),
(3, 3, "Green & Black's organic 85% dark chocolate bar", 2, 1.99),
(4, 4, "Activia fat free cherry yogurts", 3, 2.00),
(5, 5, "Green & Blacks Organic Chocolate Ice Cream", 2, 4.20);

select name, quantity * price as total from groceries;

```

--functions
```
SELECT name , round(quantity * price) as rounded_total
FROM grocerie


-- Run
CREATE TABLE customers (_key string, first_name string, last_name string) FORMAT(string, Avro);

-- And then
INSERT INTO customers(_key, first_name, last_name)
VALUES
("mikejones", "Mike", "Jones"),
("anasmith", "Ana", "Smith"),
("shannonelliott", "Shannon","Elliott");

--concat 2 columns
SELECT CONCAT(first_name, " ", last_name) as name
FROM customers;

```

--filtering
```
SELECT name
     , price
FROM groceries
WHERE price >= 2.0

SELECT *
FROM customers
WHERE LEN(last_name)=5

SELECT *
FROM customers
WHERE first_name like '%Ana%'
```

--missing values (nulls)
```
-- Run
CREATE TABLE customers_json (_key string, first_name string, last_name string, middle_name string) FORMAT(string, json);

-- And then
INSERT INTO customers_json(_key, first_name, last_name, middle_name) VALUES("mikejones", "Mike", "Jones", "Albert");
INSERT INTO customers_json(_key, first_name, last_name) VALUES("anasmith", "Ana", "Smith");
INSERT INTO customers_json(_key, first_name, last_name) VALUES("shannonelliott", "Shannon","Elliott");

SELECT *
FROM customers_json
WHERE `exists`(middle_name)

--or
SELECT *
FROM customers_json
WHERE middle_name is not NULL

```

--limit the ooutput
```
--set max data size returned by query to 1mb
SET max.size="1m";


select * from groceries limit 2,3

```


--partitioned table
```
-- Run
CREATE TABLE customers_partitioned (_key string, first_name string, last_name string) FORMAT(string, Avro) properties(partitions = 3);

-- And then
INSERT INTO customers_partitioned(_key, first_name, last_name)
VALUES
("mikejones", "Mike", "Jones"),
("anasmith", "Ana", "Smith"),
("shannonelliott", "Shannon","Elliott"),
("tomwood", "Tom","Wood"),
("adelewatson", "Adele","Watson"),
("mariasanchez", "Maria", "Sanchez");

--only records from partition 0
select * from customers_partitioned
where _meta.partition=0


--recs from partition 0 or 1
select * from customers_partitioned
where _meta.partition in (0,1) 

```

--aggregation
```
SELECT SUM(quantity * price) as amount
FROM groceries


--group by
CREATE TABLE customer (firstName string, lastName string, city string, country string, phone string) FORMAT(string, avro);

INSERT INTO customer (_key,firstName, lastName, city, country, phone)
VALUES
('1','Craig', 'Smith', 'New York', 'USA', '1-01-993 2800'),
('2','William', 'Maugham','Toronto','Canada','+1-613-555-0110'),
('3','William', 'Anthony','Leeds','UK', '+44 1632 960427'),
('4','S.B.','Swaminathan','Bangalore','India','+91 7513200000'),
('5','Thomas','Morgan','Arnsberg','Germany','+49-89-636-48018'),
('6','Thomas', 'Merton','New York','USA', '+1-617-555-0147'),
('7','Piers','Gibson','London','UK', '+44 1632 960269'),
('8','Nikolai','Dewey','Atlanta','USA','+1-404-555-0178'),
('9','Marquis', 'de Ellis','Rio De Janeiro','Brazil','+55 21 5555 5555'),
('10','Joseph', 'Milton','Houston','USA','+1-202-555-0153'),
('11','John','Betjeman Hunter','Sydney','Australia','+61 1900 654 321'),
('12','Evan', 'Hayek','Vancouver','Canada','+1-613-555-0130'),
('13','E.','Howard','Adelaide','Australia','+61 491 570 157'),
('14','C. J.', 'Wilde','London','UK','+44 1632 960111'),
('15','Butler', 'Andre','Florida','USA','+1-202-555-0107');

SELECT COUNT(*) as count
     , country
FROM customer
GROUP BY country


SELECT COUNT(*) as count
     , country
FROM customer
GROUP BY country
HAVING count > 1

```

--
nested queries
```
SELECT *
FROM (
    SELECT COUNT(*) as count
         , country
    FROM customer
    GROUP BY country)
WHERE count > 1

```


--joins
```
CREATE TABLE orders(_key int, orderDate string, customerId string, amount double) format(int, avro);

INSERT INTO orders (_key, orderDate, customerId, amount)
VALUES
(1, '2018-10-01', '1', 200.50),
(2, '2018-10-11', '1', 813.00),
(3, '2018-10-11', '3', 625.20),
(4, '2018-10-11', '14', 730.00),
(5, '2018-10-11', '10', 440.00),
(6, '2018-10-11', '9', 444.80);


SELECT o._key as orderNumber
    , o.amount as totalAmount
    , c.firstName as frstName
    , c.lastName  as lstName
    , c.city   as city
    , c.country as country
FROM orders o INNER JOIN customer c
    ON o.customerId = c._key


```
