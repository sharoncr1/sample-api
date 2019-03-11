package com.mycom.thirdapp.controllers;

import com.mycom.thirdapp.student.StudentDetails;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.mycom.thirdapp.models.StudentDetailsService;
import com.mycom.thirdapp.student.StudentRepository;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;



import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentControllerTest {

   MockMvc mockMvc;

    @MockBean
    StudentRepository studentRepository;

    @Autowired
    StudentDetailsService studentDetailsService;

    @Autowired
    StudentController studentController;


    @Before
    public void setUp() throws Exception {
        mockMvc= MockMvcBuilders.standaloneSetup(studentController).build();
    }

    @Test
    public void welcome() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/hello")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Hello World"));
    }

    @Test
    public void getAllStudentsTest(){
        when(studentRepository.findAll()).thenReturn(Stream.of
                (new StudentDetails("name1",1,"st001"),
                new StudentDetails("name2",2,"st002")).collect(Collectors.toList()));
        assertEquals(2,studentController.getAllStudentDetails().size());

    }


    @Test
    public void getStudentDetails() {
        when(studentController.getStudentDetails("st001")).thenReturn(
            new StudentDetails("name1",6,"st001"));
        Assert.assertEquals("name1",studentController.getStudentDetails("st001").getStudentName());
    }



    @Test
    public void deleteStudentDetails() {
        StudentDetails student=new StudentDetails("name1",6,"st001");
        studentController.deleteStudentDetails("st001");
        verify(studentRepository,times(1)).delete("st001");
    }
}