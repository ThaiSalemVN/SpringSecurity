USE master
GO
IF EXISTS(SELECT * FROM sys.databases WHERE name = 'TestSecurity')
			DROP DATABASE TestSecurity

GO
---Tạo database
CREATE DATABASE TestSecurity
GO
--Chọn database làm việc
USE TestSecurity
GO
--Tạo bảng User có Role_ID
CREATE TABLE Users(
	UserName varchar(50) not null,
	Password varchar(255) not null,
	FullName nvarchar(50) not null,
	RoleID varchar(50) not null,
	PRIMARY KEY (UserName)
)
GO
--Insert to table Users pass 123
INSERT INTO Users VALUES('Admin',	'$2a$10$X6cJgcyflgYgeAnlC0W26.3G18hOWmTGeaQNrV9igiyWeHy8uzHT2', N'Người dùng', 'ROLE_USER'),
						('Admin2',	'$2a$10$X6cJgcyflgYgeAnlC0W26.3G18hOWmTGeaQNrV9igiyWeHy8uzHT2', N'Quản lý', 'ROLE_ADMIN')

GO
Select * from Users