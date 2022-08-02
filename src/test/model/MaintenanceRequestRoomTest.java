package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MaintenanceRequestRoomTest {

    private MaintenanceRequestRoom testMaintenanceRequestRoom;
    private MaintenanceRequest request1;
    private MaintenanceRequest request2;
    private MaintenanceRequest request3;


    @BeforeEach
    public void setUp(){
        testMaintenanceRequestRoom = new MaintenanceRequestRoom("Test Room");
        request1 = new MaintenanceRequest("Kitchen Maintenance", "Dishwasher not working");
        request2 = new MaintenanceRequest("Living room maintenance", "Dirty couch");
        request3 = new MaintenanceRequest("Room Maintenance", "Broken windows");
    }

    @Test
    public void testConstructor(){
        assertEquals(0, testMaintenanceRequestRoom.getRequests().size());
        assertEquals("Test Room",testMaintenanceRequestRoom.getRequestRoomName());
    }

    @Test

    public void testSubmitRequest(){
        //add request first

        assertTrue(testMaintenanceRequestRoom.submitRequest(request1));
        assertEquals(1, testMaintenanceRequestRoom.getRequests().size());

        //cumulative effect

        assertTrue(testMaintenanceRequestRoom.submitRequest(request2));
        assertEquals(2, testMaintenanceRequestRoom.getRequests().size());

        //request already in the list (already submitted)

        assertFalse(testMaintenanceRequestRoom.submitRequest(request1));
        assertEquals(2, testMaintenanceRequestRoom.getRequests().size());
    }

    @Test

    public void testDeleteRequest(){
        //course is not on list
        assertFalse(testMaintenanceRequestRoom.deleteRequest(request1));
        assertEquals(0, testMaintenanceRequestRoom.getRequests().size());

        //adding and deleting
        testMaintenanceRequestRoom.submitRequest(request1);
        testMaintenanceRequestRoom.submitRequest(request2);
        testMaintenanceRequestRoom.submitRequest(request3);

        assertEquals(3, testMaintenanceRequestRoom.getRequests().size());

        //deleting them

        assertTrue(testMaintenanceRequestRoom.deleteRequest(request1));
        assertEquals(2, testMaintenanceRequestRoom.getRequests().size());

        assertTrue(testMaintenanceRequestRoom.deleteRequest(request2));
        assertEquals(1, testMaintenanceRequestRoom.getRequests().size());

    }

    @Test
    public void testGetRequests(){

        //adding and deleting
        testMaintenanceRequestRoom.submitRequest(request1);
        testMaintenanceRequestRoom.submitRequest(request2);
        testMaintenanceRequestRoom.submitRequest(request3);
        assertEquals(request1,testMaintenanceRequestRoom.getRequests().get(0));
        assertEquals(request2,testMaintenanceRequestRoom.getRequests().get(1));
        assertEquals(request3,testMaintenanceRequestRoom.getRequests().get(2));


        testMaintenanceRequestRoom.deleteRequest(request1);
        assertEquals(request2,testMaintenanceRequestRoom.getRequests().get(0));
        assertEquals(request3,testMaintenanceRequestRoom.getRequests().get(1));

    }

    @Test
    public void testGetRequestRoomName(){
        assertEquals("Test Room",testMaintenanceRequestRoom.getRequestRoomName());
    }
    @Test
    public void testToJson(){
        //
    }

}
