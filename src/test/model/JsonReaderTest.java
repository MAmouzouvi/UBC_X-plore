package model;

import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest{

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Student student = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyCourseRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWorkRoom.json");
        try {
            Student student = reader.read();
            assertEquals("Makafui Amouzouvi", student.getStudentName());
            assertEquals(0, student.getCourseRoom().getCourseRoomSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test

    void testReaderGeneralCourseRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralStudent.json");
        try {
            Student student = reader.read();
            assertEquals("courseRoom", student.getCourseRoom().getCourseRoomName());
            List<Course> courses = student.getStudentCourses();
            assertEquals(2, courses.size());
            assertEquals("Physics",courses.get(0).getCourseName());
            assertEquals("Statistics",courses.get(1).getCourseName());


            assertEquals("maintenanceRequestRoom", student.getRequestRoom().getRequestRoomName());
            List<MaintenanceRequest> requests = student.getRequestRoom().getRequests();
            assertEquals(2, requests.size());
            assertEquals("kitchen issue",requests.get(0).getTitle());
            assertEquals("dishwasher is not working",requests.get(0).getProblem());
            assertEquals("bathroom issue",requests.get(1).getTitle());
            assertEquals("floading from sink",requests.get(1).getProblem());

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }


}
