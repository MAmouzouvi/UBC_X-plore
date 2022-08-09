package persistence;

import model.*;
import model.exceptions.NegativeAmountException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JsonReader {

    private String source;
    private Student student;

    //EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    //EFFECTS: reads student from file and returns it;
    //throws IOException if an error occurs reading data from file
    public Student read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseStudent(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses student from JSON object and returns it
    private Student parseStudent(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        student = new Student(name);
      //  addCourses(student.getCourseRoom(),jsonObject);
        parseCourseRoom(jsonObject);
        try {
            parseAccount(jsonObject);
        } catch (NegativeAmountException e) {
            System.out.println("Error :Negative amount!");
        }
        parseMaintenanceRequestRoom(jsonObject);
        //addStores(sr, jsonObject);
        return student;
    }

    // EFFECTS: parses courseRoom from JSON object and returns it
    private void parseCourseRoom(JSONObject jsonObject) {
        JSONObject jsonObject1 = jsonObject.getJSONObject("courseRoom");
        String name = jsonObject1.getString("courseRoomName");
        try {
            addCourses(student.getCourseRoom(), jsonObject);
        } catch (NegativeAmountException e) {
            System.out.println("Course cost is negative : Illegal amount");
        }
    }

    private void addCourses(CourseRoom cr, JSONObject jsonObject) throws NegativeAmountException {
        JSONObject jsonObject1 = jsonObject.getJSONObject("courseRoom");
        JSONArray jsonArray = jsonObject1.getJSONArray("courses");
        for (Object json : jsonArray) {
            JSONObject nextCourse = (JSONObject) json;
            addCourse(cr, nextCourse);
        }
    }

    private void addCourse(CourseRoom cr, JSONObject jsonObject) throws NegativeAmountException {
        String name = jsonObject.getString("courseName");
        int cost = jsonObject.getInt("cost");
        Course course = new Course(name, cost);
        cr.addCourse(course);
    }


    // EFFECTS: parses courseRoom from JSON object and returns it
    private void parseMaintenanceRequestRoom(JSONObject jsonObject) {


        JSONObject jsonObject1 = jsonObject.getJSONObject("maintenanceRequestRoom");
        String name = jsonObject1.getString("requestListName");
        MaintenanceRequestRoom mr = new MaintenanceRequestRoom(name);
        addRequests(student.getRequestRoom(), jsonObject);
    }

    private void addRequests(MaintenanceRequestRoom mr, JSONObject jsonObject) {
        JSONObject jsonObject1 = jsonObject.getJSONObject("maintenanceRequestRoom");
        JSONArray jsonArray = jsonObject1.getJSONArray("requests");
        for (Object json : jsonArray) {
            JSONObject nextRequest = (JSONObject) json;
            addRequest(mr, nextRequest);
        }
    }

    private void addRequest(MaintenanceRequestRoom mr, JSONObject jsonObject) {
        String title = jsonObject.getString("title");
        String problem = jsonObject.getString("problem");
        MaintenanceRequest request = new MaintenanceRequest(title, problem);
        mr.submitRequest(request);
    }

    private void parseAccount(JSONObject jsonObject) throws NegativeAmountException {
        JSONObject jsonObject1 = jsonObject.getJSONObject("account");
        int balance = jsonObject1.getInt("balance");
        student.getAccount().deposit(balance);
    }

}
