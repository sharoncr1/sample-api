alter table attendance add CONSTRAINT CHK_course1_attendance CHECK(course1>=0 AND course1<=100.00);
alter table attendance add CONSTRAINT CHK_course2_attendance CHECK(course2>=0 AND course2<=100.00);
alter table attendance add CONSTRAINT CHK_course3_attendance CHECK(course3>=0 AND course3<=100.00);