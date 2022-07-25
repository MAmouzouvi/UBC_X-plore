package model;

import java.util.LinkedList;
import java.util.List;


//represent a collection of courses with a name
public class CourseRoom {
    private String name;
    private List<Course> courseList;


    //create a new arraylist of courses
    //the list is empty
    public CourseRoom(String name) {
        this.name = name;
        this.courseList = new LinkedList<>();
    }

    //MODIFIES : this.
    //EFFECTS: if the course is not on the list ,
    // adds course at the end of the list and return true
    //if the course is already on the list, return false
    public boolean addCourse(Course course) {
        if (!courseList.contains(course)) {
            courseList.add(course);
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
        if (courseList.contains(course)) {
            courseList.remove(course);
            return true;
        } else {
            return false;
        }
    }

    //EFFECTS: return true if the course list contains course, false otherwise
    public boolean contains(Course course) {
        return courseList.contains(course);
    }

    //EFFECTS: returns the number of courses in the course list
    public int getCourseRoomSize() {
        return courseList.size();
    }


    //EFFECTS: returns the courses in the course list
    public List<Course> getCourseList() {
        return courseList;
    }


    //EFFECTS: returns the course room name
    public String getCourseRoomName() {
        return name;
    }
}
