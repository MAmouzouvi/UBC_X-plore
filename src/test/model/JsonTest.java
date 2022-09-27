package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkStudent(String name, CourseRoom courses, Student student){
        assertEquals(name, student.getStudentName());
    }

}
