create table coursestandard(
    standard int(2) primary key,
    course1 char(3),
    course2 char(3),
    course3 char(3)
);

alter table coursestandard add foreign key (course1) references course(id);
alter table coursestandard add foreign key (course2) references course(id);
alter table coursestandard add foreign key (course3) references course(id);