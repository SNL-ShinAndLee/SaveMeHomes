CREATE SCHEMA SaveMeHomesDB;
use SaveMeHomesDB;

CREATE TABLE User (
idx INT auto_increment NOT NULL,
userId VARCHAR(20) NOT NULL UNIQUE,
userPass VARCHAR(20) NOT NULL,
userName VARCHAR(10) NOT NULL,
userRole INT DEFAULT 0,
userEmail VARCHAR(30),
userAddress VARCHAR(30),
PRIMARY KEY (idx)
);

CREATE TABLE Notice (
idx INT auto_increment NOT NULL,
noticeWriter VARCHAR(20) NOT NULL,
noticeTitle VARCHAR(30) NOT NULL,
noticeDate datetime DEFAULT NOW(),
noticeContent VARCHAR(1000),
primary key (idx),
foreign key (noticeWriter) REFERENCES User (userID)
);

ALTER TABLE Notice ADD CONSTRAINT noticeWriter FOREIGN KEY (noticeWriter) REFERENCES User (userId) ON DELETE CASCADE;

CREATE TABLE Board (
idx INT auto_increment NOT NULL,
boardWriter VARCHAR(20) NOT NULL,
boardTitle VARCHAR(30) NOT NULL,
boardDate datetime DEFAULT NOW(),
boardContent VARCHAR(1000),
primary key (idx),
foreign key (boardWriter) REFERENCES User (userID)
);

ALTER TABLE Board ADD CONSTRAINT boardWriter FOREIGN KEY (boardWriter) REFERENCES User (userId) ON DELETE CASCADE;

CREATE TABLE City (
cityCode BIGINT NOT NULL,
citySido VARCHAR(30) NOT NULL,
cityGugun VARCHAR(30),
cityDong VARCHAR(30),
cityLi VARCHAR(30),
PRIMARY KEY (cityCode)
);

CREATE TABLE Apartment (
idx INT auto_increment NOT NULL,
apartName VARCHAR(100) not NULL,
dealAmount INT,
buildYear INT,
landArea float(10,4),
dong VARCHAR(10),
dealDate date,
dedicatedArea float(10,4),
lotNum varchar(10),
cityCode bigint,
floor int,
PRIMARY KEY (idx)
);

CREATE TABLE FavoriteArea (
idx INT auto_increment NOT NULL,
userId VARCHAR(20) NOT NULL,
cityCode BIGINT NOT NULL,
FOREIGN KEY (cityCode) REFERENCES City (cityCode),
FOREIGN KEY (userId) REFERENCES User (userId),
PRIMARY KEY (idx)
);

ALTER TABLE FavoriteArea ADD CONSTRAINT cityCode FOREIGN KEY (cityCode) REFERENCES City (cityCode) ON DELETE CASCADE;
ALTER TABLE FavoriteArea ADD CONSTRAINT userId FOREIGN KEY (userId) REFERENCES User (userId) ON DELETE CASCADE;

CREATE TABLE NearByStore (
idx INT auto_increment NOT NULL,
storeName VARCHAR(30),
classCodeC VARCHAR(20) Not null,
classNameC VARCHAR(30) NOT NULL,
roadAddress VARCHAR(50) NOT NULL,
lat FLOAT(15,10) NOT NULL,
lng FLOAT(15,10) NOT NULL,
PRIMARY KEY (idx),
FOREIGN KEY (classCodeC) REFERENCES StoreClassC (classCodeC)
);

ALTER TABLE NearByStore ADD CONSTRAINT classCodeC FOREIGN KEY (classCodeC) REFERENCES StoreClassC (classCodeC) ON DELETE CASCADE;

CREATE TABLE StoreClassA (
idx INT auto_increment NOT NULL,
classCodeA VARCHAR(20) Not null UNIQUE,
classNameA VARCHAR(30) NOT NULL,
PRIMARY KEY (idx)
);

CREATE TABLE StoreClassB (
idx INT auto_increment NOT NULL,
classCodeA VARCHAR(20) NOT NULL,
classCodeB VARCHAR(20) Not null UNIQUE,
classNameB VARCHAR(30) NOT NULL,
PRIMARY KEY (idx),
FOREIGN KEY (classCodeA) REFERENCES StoreClassA (classCodeA)
);

ALTER TABLE StoreClassB ADD CONSTRAINT classCodeA FOREIGN KEY (classCodeA) REFERENCES StoreClassA (classCodeA) ON DELETE CASCADE;

CREATE TABLE StoreClassC (
idx INT auto_increment NOT NULL,
classCodeB VARCHAR(20) NOT NULL,
classCodeC VARCHAR(20) Not null UNIQUE,
classNameC VARCHAR(30) NOT NULL,
PRIMARY KEY (idx),
FOREIGN KEY (classCodeB) REFERENCES StoreClassB (classCodeB)
);

ALTER TABLE StoreClassC ADD CONSTRAINT classCodeB FOREIGN KEY (classCodeB) REFERENCES StoreClassB (classCodeB) ON DELETE CASCADE;
