package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Unit tests for the Event class
 */
public class EventTest {
    private Date date;
    private Event e;
    private Event e1;
    private Date d;

    //NOTE: these tests might fail if time at which line (2) below is executed
    //is different from time that line (1) is executed.  Lines (1) and (2) must
    //run in same millisecond for this test to make sense and pass.

    @BeforeEach
    public void runBefore() {

        e = new Event("Sensor open at door");
        e1 = new Event("Sensor ");// (1)
        d = Calendar.getInstance().getTime();   // (2)
    }

    @Test
    public void testEvent() {
        assertEquals("Sensor open at door", e.getDescription());
        //assertEquals(d, e.getDate());
    }

    @Test
    public void testToString() {
        assertEquals(d.toString() + "\n" + "Sensor open at door", e.toString());
    }


    @Test
    public void testHashCode(){
        assertEquals(1182780560,e.hashCode());
    }

    @Test
    public void testEquals(){
        assertFalse(e.equals(e1));
    }

    @Test
    public void testGetDescription(){
        assertEquals("Sensor open at door",e.getDescription());
    }

    @Test
    public void testGetDate(){
        date = new Date();
        assertFalse(e.getDate().equals(date));
    }

}
