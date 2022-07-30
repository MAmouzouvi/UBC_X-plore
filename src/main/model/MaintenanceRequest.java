package model;

//Represents a housing maintenance request that a student can send to
//their school
public class MaintenanceRequest {

    private String title;
    private String problem;

    //EFFECTS: creates a maintenance request object with a title
    // and a problem description
    public MaintenanceRequest(String title, String problem) {
        this.title = title;
        this.problem = problem;
    }


    //EFFECTS: returns the title of the Maintenance
    // request object
    public String getTitle() {
        return this.title;

    }


    //EFFECTS: returns the problem description of the Maintenance
    // request object
    public String getProblem() {
        return this.problem;
    }
}
