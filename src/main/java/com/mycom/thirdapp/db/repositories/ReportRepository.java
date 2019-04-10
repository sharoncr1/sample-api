package com.mycom.thirdapp.db.repositories;

import com.mycom.thirdapp.db.models.Report;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ReportRepository extends CrudRepository<Report,String> {

//     String got as argument is unable to use in query since it internally adds quotes which is illegal in mysql
//    @Query(
//            value = "select s.id,c.id as courseid,c.name as coursetitle,t.name as tutorname,a.course1 as attendance from student s " +
//                    "inner join coursestandard cs on s.standard=cs.standard " +
//                    "inner join course c on c.id=cs.course1 " +
//                    "inner join teachescourse ts on ts.courseid=c.id " +
//                    "inner join teacher t on t.id=ts.teacherid " +
//                    "inner join attendance a on a.studentid=s.id " +
//                    "where s.id= :studentid or s.id= :course",
//            nativeQuery = true)
//    Report generateReport(@Param("studentid")String studentid,@Param("course")String course);

    @Query(
            value = "select s.id,c.id as courseid,c.name as coursetitle,t.name as tutorname,a.course1 as attendance from student s " +
                    "inner join coursestandard cs on s.standard=cs.standard " +
                    "inner join course c on c.id=cs.course1 " +
                    "inner join teachescourse ts on ts.courseid=c.id " +
                    "inner join teacher t on t.id=ts.teacherid " +
                    "inner join attendance a on a.studentid=s.id " +
                    "where s.id= :studentid",
            nativeQuery = true)
    Report generateCourse1Report(@Param("studentid")String studentid);

    @Query(
            value = "select s.id,c.id as courseid,c.name as coursetitle,t.name as tutorname,a.course2 as attendance from student s " +
                    "inner join coursestandard cs on s.standard=cs.standard " +
                    "inner join course c on c.id=cs.course2 " +
                    "inner join teachescourse ts on ts.courseid=c.id " +
                    "inner join teacher t on t.id=ts.teacherid " +
                    "inner join attendance a on a.studentid=s.id " +
                    "where s.id= :studentid",
            nativeQuery = true)
    Report generateCourse2Report(@Param("studentid")String studentid);

    @Query(
            value = "select s.id,c.id as courseid,c.name as coursetitle,t.name as tutorname,a.course3 as attendance from student s " +
                    "inner join coursestandard cs on s.standard=cs.standard " +
                    "inner join course c on c.id=cs.course3 " +
                    "inner join teachescourse ts on ts.courseid=c.id " +
                    "inner join teacher t on t.id=ts.teacherid " +
                    "inner join attendance a on a.studentid=s.id " +
                    "where s.id= :studentid",
            nativeQuery = true)
    Report generateCourse3Report(@Param("studentid")String studentid);
}



