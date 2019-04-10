package com.mycom.thirdapp.services;

import com.mycom.thirdapp.controllers.StudentController;
import com.mycom.thirdapp.db.models.*;
import com.mycom.thirdapp.db.repositories.CourseRepository;
import com.mycom.thirdapp.db.repositories.CourseStandardRepository;
import com.mycom.thirdapp.db.repositories.TeacherRepository;
import com.mycom.thirdapp.db.repositories.TeachesCourseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseStandardRepository courseStandardRepository;

    @Autowired
    private TeachesCourseRepository teachesCourseRepository;

    final Logger logger= LoggerFactory.getLogger(CourseService.class);

    public List<CourseStandard> getAllStandards() {
        List<CourseStandard> standards=new ArrayList<>();
        courseStandardRepository.findAll().forEach(standards::add);
        return standards;
    }

    public List<Course> getAllByStanadard(int standard) {
        List<Course> courses = new ArrayList<>();
        List<CourseStandard> courseStandard=courseStandardRepository.findCoursesOfStandard(standard);
        if(courseStandard.isEmpty())
            logger.warn("no records returned");
        //finding courseids with standard from courseStandard table
        //then finding the names of those courses from the courses table
        courses.add(courseRepository.findOne(courseStandard.get(0).getCourse1()));
        courses.add(courseRepository.findOne(courseStandard.get(0).getCourse2()));
        courses.add(courseRepository.findOne(courseStandard.get(0).getCourse3()));
        return courses;
    }

    public TeachesCourse findTutorId(String courseId) {
        return teachesCourseRepository.findOne(courseId);
    }

    public Teacher findTeacher(String teacherid) {
        return teacherRepository.findOne(teacherid);
    }
}
