create table if not exists attendance(
    studentid char(5),
    course1 decimal(5,2),
    course2 decimal(5,2),
    course3 decimal(5,2),
    average decimal(5,2),
    eligibility varchar(15)
);