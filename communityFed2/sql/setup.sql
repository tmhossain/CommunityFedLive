1.  Download mysql community version
2.  Using the command prompt go to my mysql\bin folder and connect using the default user root. Default user root doesn't have a password. 
	mysql -u root
3.  Create database communityfed.
	create database communityfed;
4.  create user community with the password fed
 	create user 'community' identified by 'fed';
 	grant all privileges on *.* to 'community'@'localhost' with grant option;
5.  Grant all privileges to the user community
	grant all privileges on communityfed.* to 'community'@'localhost' identified by 'fed';
6.  exit from mysql. Just type exit and hit enter
7.  connect to mysql using user community
	mysql -u community -p
8.  connect to communityfed database
	use communityfed
9.  Create the database objects (sequences, tables, triggers)

drop table CF_USER;
create table CF_USER (id int  AUTO_INCREMENT PRIMARY key,
FIRST_NAME VARCHAR(100),
LAST_NAME VARCHAR(100),
EMAIL VARCHAR(100),
PHONE VARCHAR(20),
DOB date,
SHOW_AGE VARCHAR(1),
PROFESSION VARCHAR(100),
LANGUAGE VARCHAR(100),
USER_TYPE VARCHAR(100),
REASON_GIVING VARCHAR(100),
STATUS VARCHAR(1),
INSERTED_DT datetime,
UPDATED_DT datetime);
delete from cf_user;
INSERT INTO CF_USER (FIRST_NAME, LAST_NAME, EMAIL, PHONE, DOB, SHOW_AGE, PROFESSION, LANGUAGE, USER_TYPE, REASON_GIVING, INSERTED_dT, UPDATEd_DT) values
('Vali','Shaik','reachvali@yahoo.com','512-512-1234',sysdate(),'N', 'lecturer', 'English','giver', 'charity', sysdate(), sysdate());
SELECT * FROM CF_USER;

drop table CF_REFERENCE_CODE;
CREATE TABLE CF_REFERENCE_CODE (id int  AUTO_INCREMENT PRIMARY key,
REF_CATEGORY VARCHAR(20),
REF_CODE VARCHAR(20),
REF_CODE_DESCRIPTION VARCHAR(200),
XREF_CODE VARCHAR(20),
STATUS VARCHAR(1),
INSERTED_DT datetime,
UPDATED_DT datetime);
delete from cf_reference_code;
insert into cf_reference_code (ref_category, ref_code, ref_code_description, inserted_Dt, updated_Dt) values ('USERTYPE', 'GIVER', 'GIVER',SYSDATE(), SYSDATE());
COMMIT;

drop table CF_ADDRESS;
CREATE TABLE CF_ADDRESS (id int  AUTO_INCREMENT PRIMARY key,
USER_ID int,
ADDRESS_TYPE VARCHAR(20),
STREET VARCHAR(200),
CITY VARCHAR(20),
POSTAL_ZIP VARCHAR(20),
STATE_PROVINCE VARCHAR(20),
COUNTRY VARCHAR(20),
STATUS VARCHAR(1),
INSERTED_DT datetime,
UPDATED_DT datetime);
delete from CF_ADDRESS;
insert into CF_ADDRESS (USER_ID, ADDRESS_TYPE, STREET, CITY, POSTAL_ZIP, STATE_PROVINCE, COUNTRY, STATUS, INSERTED_DT, UPDATED_DT) values (1, 1, 'dyer crossing way', 'round rock','23232','tx','usa','A',sysdate(),sysdate());
select * from cf_address;

drop table CF_OFFER;
CREATE TABLE CF_OFFER (id int  AUTO_INCREMENT PRIMARY key,
USER_ID int,
OFFER_CATEGORY_ID  VARCHAR(20),
ITEM_ID  VARCHAR(20),
QUANTITY int,
PRICE_PER_POUND decimal(8,2),
FREE varchar(1),
OFFER_TITLE  VARCHAR(200),
OFFER_DATE date,
STATUS varchar(1),
INSERTED_DT datetime,
UPDATED_DT datetime);

drop table CF_PICTURE;
CREATE TABLE CF_PICTURE (id int  AUTO_INCREMENT PRIMARY key,
OFFER_ID int,
PICTURE_ID   VARCHAR(200),
STATUS  VARCHAR(1),
INSERTED_DT datetime,
UPDATED_DT datetime);

drop table CF_RECEIVER_REVIEW;
CREATE TABLE CF_RECEIVER_REVIEW (id int  AUTO_INCREMENT PRIMARY key,
USER_ID int,
OFFER_ID int, 
OVERALL_SATISFACTION int, 
REVIEW   VARCHAR(2000),
SAFETY_RATING int,
LOCATION_RATING int,
VALUE_FOR_MONEY_RATING int,
COMMUNICATION_RATING int,
STATUS varchar(1),
INSERTED_DT datetime,
UPDATED_DT datetime);

drop table CF_GIVER_COMMENT;
CREATE TABLE CF_GIVER_COMMENT (id int  AUTO_INCREMENT PRIMARY key,
REVIEW_ID int, 
GIVER_ID int, 
GIVER_COMMENTS  VARCHAR(2000),
STATUS  VARCHAR(1),
INSERTED_DT datetime,
UPDATED_DT datetime);


//alter table cf_user add USER_NAME varchar(50)
//alter table cf_user add PASSWORD varchar(50)
