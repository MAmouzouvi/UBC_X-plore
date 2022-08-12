package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.LinkedList;
import java.util.List;

//represents a collection of maintenance requests submitted
public class MaintenanceRequestRoom implements Writable {

    private String requestListName;
    private List<MaintenanceRequest> requests;

    //EFFECTS: create a collection of maintenance requests with a name. List is empty
    public MaintenanceRequestRoom(String name) {
        this.requestListName = name;
        requests = new LinkedList<>();

    }


    //MODIFIES: this
    //EFFECTS: adds request to the list of submitted requests
    public boolean submitRequest(MaintenanceRequest request) {
        if (!requests.contains(request)) {
            requests.add(request);
            EventLog.getInstance().logEvent(new Event("A maintenance request was submitted to the "
                    + requestListName));
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
            EventLog.getInstance().logEvent(new Event("A maintenance request was deleted from the "
                    + requestListName));
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


    //EFFECTS: returns the Maintenance Requests room name
    public String getRequestRoomName() {
        return this.requestListName;
    }


    @Override
    //EFFECTS: write maintenance request room as a JSON objects
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("requestListName",requestListName);
        json.put("requests",requestsToJson());

        return json;
    }

    //EFFECTS: write request list as JSONArray
    private JSONArray requestsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (MaintenanceRequest request : requests) {
            jsonArray.put(request.toJson());
        }
        return jsonArray;
    }

}
