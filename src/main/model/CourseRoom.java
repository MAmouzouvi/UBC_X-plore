package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.LinkedList;
import java.util.List;


//represent a collection of courses with a name
public class CourseRoom implements Writable {
    private String courseRoomName;
    private List<Course> courses;


    //create a new arraylist of courses
    //the list is empty
    public CourseRoom(String name) {
        this.courseRoomName = name;
        this.courses = new LinkedList<>();
    }

    //MODIFIES : this.
    //EFFECTS: if the course is not on the list ,
    // adds course at the end of the list and return true
    //if the course is already on the list, return false
    public boolean addCourse(Course course) {
        if (!courses.contains(course)) {
            courses.add(course);
            return true;
        } else {
            return false;
        }
    }

    //MODIFIES : this.
    //EFFECTS: if the course is not on the list ,
    // return false
    //if the course is  on the list, remove it and
    // return true
    public boolean removeCourse(Course course) {
        if (courses.contains(course)) {
            courses.remove(course);
            return true;
        } else {
            return false;
        }
    }

    //EFFECTS: return true if the course list contains course, false otherwise
    public boolean contains(Course course) {
        return courses.contains(course);
    }

    //EFFECTS: returns the number of courses in the course list
    public int getCourseRoomSize() {
        return courses.size();
    }


    //EFFECTS: returns the courses in the course list
    public List<Course> getCourses() {
        return courses;
    }


    //EFFECTS: returns the course room name
    public String getCourseRoomName() {
        return courseRoomName;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("courseRoomName", courseRoomName);
        json.put("courses", coursesToJson());
        return json;
    }

    private JSONArray coursesToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Course course : courses) {
            jsonArray.put(course.toJson());
        }
        return jsonArray;
    }
}
