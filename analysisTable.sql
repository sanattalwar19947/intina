CREATE TABLE MONTHLYREPORT
(
WAREHOUSE_NO varchar2(40) not null;
AVG_REQ number(5) not null,
AVG_SERVED number(5) not null,
MISS number(5) not null,
EFF number(5) not null
);
CREATE TABLE technician_avg_status
(
WAREHOUSE_NO varchar2(40) not null;
OCCUPIED number(5) not null,
FREE number(5) not null,
OTW number(5) not null,
);
