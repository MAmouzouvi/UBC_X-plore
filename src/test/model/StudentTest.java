package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class StudentTest {

    private Student testStudent;
    private Course testCourse1;
    private Course testCourse2;
    private Course testCourse3;

    private MaintenanceRequest request1;
    private MaintenanceRequest request2;
    private MaintenanceRequest request3;

    private MaintenanceRequestRoom testMaintenanceRequestRoom;


    @BeforeEach
    public void setUp(){
        testCourse1 = new Course("CPSC210",5000);
        testCourse2 = new Course("MATH200",3000);
        testCourse3 = new Course("UX Design", 50000);

        testStudent = new Student(1,"Makafui Amouzouvi",30000);

        testMaintenanceRequestRoom = new MaintenanceRequestRoom("Test Room");
        request1 = new MaintenanceRequest("Kitchen Maintenance", "Dishwasher not working");
        request2 = new MaintenanceRequest("Living room maintenance", "Dirty couch");
        request3 = new MaintenanceRequest("Room Maintenance", "Broken windows");

    }

    @Test
    public void testStudent(){
        assertEquals(1, testStudent.getStudentId());
        assertEquals("Makafui Amouzouvi", testStudent.getStudentName());
        assertEquals(30000, testStudent.getAccountBalance());
        assertEquals(0, testStudent.getStudentCourses().size());
    }

    @Test
    public void testRegisterToCourse(){

        //add first course
        assertTrue(testStudent.registerToCourse(testCourse1));
        assertEquals(testCourse1, testStudent.getStudentCourses().get(0));
        assertEquals(1, testStudent.getStudentCourses().size());
        assertEquals(25000, testStudent.account.getBalance());

        //add two more students for cumulative test
        assertTrue(testStudent.registerToCourse(testCourse2));
        assertEquals(testCourse2, testStudent.getStudentCourses().get(1));
        assertEquals(22000, testStudent.account.getBalance());


        //add course already registered in
        assertFalse(testStudent.registerToCourse(testCourse1));

        //add student with insufficient fund
        assertFalse(testStudent.registerToCourse(testCourse3));
    }

    @Test
    public void testDeregisterFromCourse(){

        //Student is not in the course
        assertFalse(testStudent.deregisterFromCourse(testCourse1));

        //Student is in course

           // ---- adding courses
        assertTrue(testStudent.registerToCourse(testCourse1));
        assertEquals(testCourse1,testStudent.getStudentCourses().get(0));
        assertEquals(25000,testStudent.account.getBalance());

        assertTrue(testStudent.registerToCourse(testCourse2));
        assertEquals(testCourse2,testStudent.getStudentCourses().get(1));
        assertEquals(22000,testStudent.account.getBalance());
        assertEquals(2,testStudent.getStudentCourses().size());

           //---- deregistering them from courses
        assertTrue(testStudent.deregisterFromCourse(testCourse1));
        assertEquals(27000,testStudent.getAccountBalance());
        assertEquals(1,testStudent.getStudentCourses().size());

        assertTrue(testStudent.deregisterFromCourse(testCourse2));
        assertEquals(30000,testStudent.getAccountBalance());
        assertEquals(0,testStudent.getStudentCourses().size());
    }

    @Test
    public void testGetStudentName(){
        assertEquals("Makafui Amouzouvi", testStudent.getStudentName());
    }

    @Test
            public void testGetStudentId(){
        assertEquals(1, testStudent.getStudentId());
    }

    @Test
            public void testGetStudentCourses(){
        assertEquals(0, testStudent.getStudentCourses().size());

        //adding student to the courses
        assertTrue(testStudent.registerToCourse(testCourse1));
        assertTrue(testStudent.registerToCourse(testCourse2));

        //Checking them

        assertEquals(testCourse1,testStudent.getStudentCourses().get(0));
        assertEquals(testCourse2,testStudent.getStudentCourses().get(1));
        assertEquals(2,testStudent.getStudentCourses().size());
    }

    @Test
    public void testGetAccountBalance(){
        assertEquals(30000, testStudent.getAccountBalance());
    }


    @Test

    public void testGetAccount(){
        assertEquals(30000,testStudent.getAccount().getBalance());
    }

    @Test
    public void testGetMaintenanceRequestRoom(){
        assertEquals(0, testStudent.getRequestRoom().getRequests().size());

        testStudent.requests.submitRequest(request1);
        testStudent.requests.submitRequest(request2);
        testStudent.requests.submitRequest(request3);
        assertEquals(3, testStudent.getRequestRoom().getRequests().size());
        assertEquals(request1,testStudent.getRequestRoom().getRequests().get(0));

    }
}
