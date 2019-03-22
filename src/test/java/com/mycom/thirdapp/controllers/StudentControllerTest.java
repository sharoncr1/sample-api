package com.mycom.thirdapp.controllers;

import com.mycom.thirdapp.db.models.Student;
import com.mycom.thirdapp.services.StudentService;
import com.mycom.thirdapp.db.repositories.StudentRepository;
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

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


//tests that using the mocked databases instances

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentControllerTest {

   MockMvc mockMvc;

    @MockBean
    StudentRepository studentRepository;

    @Autowired
    StudentService studentService;

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
                (new Student("name1",1,"st001"),
                new Student("name2",2,"st002")).collect(Collectors.toList()));
        assertEquals(2,studentController.getAll().size());

    }


    @Test
    public void getStudentDetails() {
        when(studentController.get("st001")).thenReturn(
            new Student("name1",6,"st001"));
        Assert.assertEquals("name1",studentController.get("st001").getName());
    }


    @Test
    public void deleteStudentDetails() {
//        Student db=new Student("name1",6,"st001");
        studentController.delete("st001");
        verify(studentRepository,times(1)).delete("st001");
    }
}