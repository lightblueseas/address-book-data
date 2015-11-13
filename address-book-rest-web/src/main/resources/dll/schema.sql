create table addresses (id int4 not null, address_comment varchar(100), geohash varchar(16), latitude varchar(12), longitude varchar(12), street varchar(64), streetnumber varchar(5), federalstate_id int4, zipcode_id int4, primary key (id));
create table countries (id int4 not null, iso3166_a2name varchar(2), iso3166_a3name varchar(3), iso3166_number varchar(3), name varchar(128), primary key (id));
create table federalstates (id int4 not null, iso3166_a2code varchar(6), name varchar(128), subdivision_category varchar(128), subdivision_name varchar(56), country_id int4, primary key (id));
create table zipcodes (id int4 not null, city varchar(128), zipcode varchar(10) not null, country_id int4, primary key (id));
