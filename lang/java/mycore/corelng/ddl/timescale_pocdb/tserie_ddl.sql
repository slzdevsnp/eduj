CREATE EXTENSION IF NOT EXISTS timescaledb CASCADE;

DROP TABLE IF EXISTS tserie;

CREATE TABLE IF NOT EXISTS tserie (
oid             INTEGER  NOT NULL,
ts              TIMESTAMPTZ NOT NULL,
tzone           VARCHAR(18),
price           DOUBLE PRECISION
);


SELECT create_hypertable ('tserie', 'ts', 'oid',100, chunk_time_interval => 86400000000);

--CREATE UNIQUE INDEX udp_idx ON tserie(oid,ts);
ALTER TABLE tserie
	ADD CONSTRAINT uniquepd_const UNIQUE (oid,ts);
