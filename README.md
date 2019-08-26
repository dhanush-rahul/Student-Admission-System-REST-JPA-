# Student-Admission-System-REST-JPA-

Database queries:(Static)

create table Colleges(collegeName varchar(20),collegeCode varchar(20) primary key);
insert into Colleges values("Admin",000");


create table login(username varchar(20) primary key,password varchar(20) not null,collegeCode varchar(5) not null, foreign key (collegeCode) references Colleges(collegeCode));
insert into login values("Dhanush","123","000");
