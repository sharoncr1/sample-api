alter table attendance ADD FOREIGN KEY (studentid) REFERENCES student(id);

--ALTER TABLE course ADD CONSTRAINT check_courses
--    CHECK (id IN ('c01', 'c02', 'c03'));