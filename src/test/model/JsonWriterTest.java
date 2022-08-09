package model;

import model.Exceptions.NegativeAmountException;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest{


    @Test
    void testWriterInvalidFile() {
        try {
            Student student = new Student("Makafui Amouzouvi");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyRooms() {
        try {
            Student student = new Student("Makafui Amouzouvi");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyRooms.json");
            writer.open();
            writer.write(student);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyRooms.json");
            student = reader.read();
            assertEquals("Makafui Amouzouvi", student.getStudentName());
            assertEquals("courseRoom",student.getCourseRoom().getCourseRoomName());
            assertEquals("maintenanceRequestRoom",student.getMaintenanceRequestRoom().getRequestRoomName());
            assertEquals(0, student.getRequestRoom().getRequests().size());
            assertEquals(0, student.getStudentCourses().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {

            Student student = new Student("Makafui");
            Course course1 = new Course("course1",1000);
            MaintenanceRequest request1 = new MaintenanceRequest("kitchen issue","broken sink");
            student.courseRoom.addCourse(course1);
            student.maintenanceRequestRoom.submitRequest(request1);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(student);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralRoom.json");
            student = reader.read();

            assertEquals("Makafui Amouzouvi", student.getStudentName());
            assertEquals("courses",student.getCourseRoom().getCourseRoomName());
            assertEquals("courses",student.getMaintenanceRequestRoom().getRequestRoomName());
            assertEquals(1, student.getRequestRoom().getRequests().size());
            assertEquals(1, student.getStudentCourses().size());

            assertEquals("kitchen issue",student.getRequestRoom().getRequests().get(0).getTitle());
            assertEquals("dishwasher is not working",student.getRequestRoom().getRequests().get(0).getProblem());
            assertEquals("course1",student.getStudentCourses().get(0).getCourseName());


        } catch (IOException e) {
            //fail("Exception should not have been thrown");
        } catch (NegativeAmountException e) {
            fail("Exception not expected");
        }
    }

}
