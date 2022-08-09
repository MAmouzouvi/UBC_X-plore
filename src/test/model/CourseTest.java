package model;

import model.Exceptions.NegativeAmountException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CourseTest {
    private Course testCourse;
    private Student testStudent1;
    private Student testStudent2;
    private Student testStudent3;
    private Student testStudent4;

    @BeforeEach
    public void setUp(){
        try {
            testCourse = new Course("CPSC210",5000);
        } catch (NegativeAmountException e) {
            fail("Exception not expected");
        }
    }

    @Test
    public void testCourse(){
        assertEquals("CPSC210",testCourse.getCourseName());
        assertEquals(5000,testCourse.getCourseCost());
        //assertEquals(0,testCourse.getStudents().size());
    }
/*

    @Test
    public void testRegisterStudent(){

        //add first student
        assertTrue(testCourse.registerStudent(testStudent1));
        assertEquals(testStudent1,testCourse.students.get(0));
        assertEquals(1,testCourse.students.size());
        assertEquals(25000,testStudent1.account.getBalance());

        //add two more students for cumulative test
        assertTrue(testCourse.registerStudent(testStudent2));
        assertEquals(testStudent2,testCourse.students.get(1));
        assertEquals(15000,testStudent2.account.getBalance());

        assertTrue(testCourse.registerStudent(testStudent3));
        assertEquals(testStudent3,testCourse.students.get(2));
        assertEquals(5000,testStudent3.account.getBalance());
        assertEquals(3,testCourse.students.size());

        //add student already in course
        assertFalse(testCourse.registerStudent(testStudent1));

        //add student with insufficient fund
        assertFalse(testCourse.registerStudent(testStudent4));
    }

    @Test
    public void testDeregisterStudent(){

        //Student is not in the course
        assertFalse(testCourse.deregisterStudent(testStudent1));

        //Student is in course

             //--- adding the students
        assertTrue(testCourse.registerStudent(testStudent1));
        assertEquals(testStudent1,testCourse.students.get(0));
        assertEquals(25000,testStudent1.account.getBalance());

        assertTrue(testCourse.registerStudent(testStudent2));
        assertEquals(testStudent2,testCourse.students.get(1));
        assertEquals(15000,testStudent2.account.getBalance());
        assertEquals(2,testCourse.students.size());

             //---- deregistering them
        assertTrue(testCourse.deregisterStudent(testStudent1));
        assertEquals(30000,testStudent1.getAccountBalance());
        assertEquals(1,testCourse.students.size());

        assertTrue(testCourse.deregisterStudent(testStudent2));
        assertEquals(20000,testStudent2.getAccountBalance());
        assertEquals(0,testCourse.students.size());

    }
*/

    @Test
    public void testGetCourseName(){
        assertEquals("CPSC210",testCourse.getCourseName());
    }

    @Test
    public void testGetCourseCost(){
        assertEquals(5000,testCourse.getCourseCost());
    }
    @Test
    public void testToJson(){
        // JSONObject jsonObject = new JSONObject();
        assertEquals("CPSC210",testCourse.toJson().get("courseName"));
    }

 /*   @Test
    public void testGetStudents(){
      //adding students to the course
        assertTrue(testCourse.registerStudent(testStudent1));
        assertTrue(testCourse.registerStudent(testStudent2));

        //Checking them

        assertEquals(testStudent1,testCourse.getStudents().get(0));
        assertEquals(testStudent2,testCourse.getStudents().get(1));
        assertEquals(2,testCourse.students.size());
    }*/



}
