package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CourseRoomTest {

    private CourseRoom testCourseRoom;
    private Course testCourse1;
    private Course testCourse2;
    private Course testCourse3;

    @BeforeEach
    public void setUp(){
        testCourseRoom = new CourseRoom("testCourseRoom");
        testCourse1 = new Course("CPSC210",5000);
        testCourse2 = new Course("MATH200",3000);
        testCourse3 = new Course("UX Design", 50000);

    }

    @Test
    public void testCourseRoom(){
        assertEquals("testCourseRoom", testCourseRoom.getCourseRoomName());
        assertEquals(0,testCourseRoom.getCourseRoomSize());
    }

    @Test
    public void testAddCourse(){
        //add first course
        assertTrue(testCourseRoom.addCourse(testCourse1));
        assertEquals(testCourse1, testCourseRoom.getCourseList().get(0));
        assertEquals(1, testCourseRoom.getCourseList().size());

        //add one more course for cumulative test
        assertTrue(testCourseRoom.addCourse(testCourse2));
        assertEquals(testCourse2, testCourseRoom.getCourseList().get(1));

        //add course already in list
        assertFalse(testCourseRoom.addCourse(testCourse1));

    }

    @Test
    public void testRemoveCourse(){
        //course is not in the list
        assertFalse(testCourseRoom.removeCourse((testCourse1)));

        //courses are in list

        // ---- adding courses
        assertTrue(testCourseRoom.addCourse(testCourse1));
        assertEquals(testCourse1,testCourseRoom.getCourseList().get(0));

        assertTrue(testCourseRoom.addCourse(testCourse2));
        assertEquals(testCourse2,testCourseRoom.getCourseList().get(1));
        assertEquals(2,testCourseRoom.getCourseList().size());

        //---- removing  courses
        assertTrue(testCourseRoom.removeCourse(testCourse1));

        assertEquals(1,testCourseRoom.getCourseList().size());

        assertTrue(testCourseRoom.removeCourse(testCourse2));
        assertEquals(0,testCourseRoom.getCourseList().size());
    }
}