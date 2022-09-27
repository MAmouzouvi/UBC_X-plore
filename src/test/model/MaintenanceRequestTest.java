package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MaintenanceRequestTest {

    private MaintenanceRequest testMaintenanceRequest;


    @BeforeEach
    public void setUp(){
        testMaintenanceRequest = new
                MaintenanceRequest("Kitchen Maintenance", "Dishwasher stopped working");
    }

    @Test
    public void testConstructor(){
        assertEquals("Kitchen Maintenance",testMaintenanceRequest.getTitle());
        assertEquals("Dishwasher stopped working",testMaintenanceRequest.getProblem());
    }


    @Test
    public void testGetTitle(){
        assertEquals("Kitchen Maintenance",testMaintenanceRequest.getTitle());
    }

    @Test
    public void testGetProblem(){
        assertEquals("Dishwasher stopped working",
                testMaintenanceRequest.getProblem());
    }

    @Test
    public void testToJson(){
        assertEquals("Kitchen Maintenance",testMaintenanceRequest.toJson().get("title"));
    }
}
