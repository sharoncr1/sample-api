alter table teachescourse ADD FOREIGN KEY (courseid) REFERENCES course(id);
alter table teachescourse ADD FOREIGN KEY (teacherid) REFERENCES teacher(id);