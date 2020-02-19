create table loginuser(
id varchar(50) not null primary key,
username varchar(50) not null unique,
password varchar(50) not null,
status varchar(1),
login_time varchar(50),
logoff_time varchar(50),
last_attempt_status varchar(1),
timestamp varchar(50),
application varchar(50),
machinename varchar(50),
userid varchar(50)
)