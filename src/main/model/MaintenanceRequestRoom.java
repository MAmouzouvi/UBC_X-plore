package model;

import java.util.LinkedList;
import java.util.List;

//represents a collection of maintenance requests submitted
public class MaintenanceRequestRoom {

    private String name;
    private List<MaintenanceRequest> requests;

    //EFFECTS: create a collection of maintenance requests with a name. List is empty
    public MaintenanceRequestRoom(String name) {
        this.name = name;
        requests = new LinkedList<>();

    }


    //MODIFIES: this
    //EFFECTS: adds request to the list of submitted requests
    public boolean submitRequest(MaintenanceRequest request) {
        if (!requests.contains(request)) {
            requests.add(request);
            return true;
        } else {
            System.out.println("This request already exists !");
            return false;
        }


    }

    //MODIFIES: this
    //EFFECTS: removes request from the list of submitted requests
    public boolean deleteRequest(MaintenanceRequest request) {
        if (requests.contains(request)) {
            requests.remove(request);
            System.out.println("The request has been removed!");
            return true;
        } else {
            System.out.println("Request not found!");
            return false;
        }
    }


    //EFFECTS: returns the request in the request list
    public List<MaintenanceRequest> getRequests() {
        return this.requests;
    }


    //EFFECTS: returns the Maintenance Requets room name
    public String getRequestRoomName() {
        return this.name;
    }


}
