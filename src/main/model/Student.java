package model;


import org.json.JSONObject;
import persistence.Writable;

import java.util.List;

//Represents a student with an id, a name, a list of courses they are registered in
//and a finances account
public class Student implements Writable {
    private String name;
    protected CourseRoom courseRoom;
    protected MaintenanceRequestRoom maintenanceRequestRoom;
    protected Account account;

    //REQUIRES:
    // EFFECTS: constructs a student with a a name, and account, a course room
    // and an empty list of registered courses
    public Student(String name) {
        this.name = name;
        this.courseRoom = new CourseRoom("courseRoom");
        this.account = new Account();
        this.maintenanceRequestRoom = new MaintenanceRequestRoom("maintenanceRequestRoom");

    }

    //MODIFIES: this
    //EFFECTS: if student is not in registered course,
    // - register them
    // - print success message
    // - return true, return false otherwise
    public boolean registerToCourse(Course course) {
        if (!courseRoom.contains(course)) {
            if (this.account.balance >= course.cost) {
                this.account.payFee(course.cost);
                this.courseRoom.addCourse(course);
                //course.students.add(this);
                System.out.println("Student " + this.name
                        + " has been successfully registered into the course !");
                return true;
            } else {
                System.out.println("Insufficient balance !");
                return false;
            }
        } else {
            System.out.println("Student is already registered in course");
            return false;
        }
    }


    //MODIFIES: this
    //EFFECTS: if student is registered in course,
    // -remove course from the list of registered courses,
    // - refund them the amount of the course
    // - print success message
    //  - return true, return false otherwise

    public boolean deregisterFromCourse(Course course) {
        if (courseRoom.contains(course)) {
            this.courseRoom.removeCourse(course);
           // course.students.remove(this);
            this.account.balance += course.cost;
            System.out.println("Student " + this.name
                    + " has been successfully removed from the course");
            return true;
        } else {
            System.out.println("Student is not registered in this course");
            return false;
        }

    }

    //EFFECTS: return the name of the student (this)
    public String getStudentName() {
        return this.name;
    }

    //EFFECTS: return the Account of the student (this)
    public Account getAccount() {
        return this.account;
    }

    //EFFECTS: return the list of courses the student is registered in
    public List<Course> getStudentCourses() {
        return this.courseRoom.getCourses();
    }

    //EFFECTS: return the courseroom
    public CourseRoom getCourseRoom() {
        return this.courseRoom;
    }

    //EFFECTS: return the list of courses the student is registered in
    public MaintenanceRequestRoom getMaintenanceRequestRoom() {
        return this.maintenanceRequestRoom;
    }

    //EFFECTS: return the student (this)'s account balance
    public int getAccountBalance() {
        return this.account.balance;
    }

    public MaintenanceRequestRoom getRequestRoom() {
        return this.maintenanceRequestRoom;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("courseRoom", courseRoom.toJson());
        json.put("maintenanceRequestRoom", maintenanceRequestRoom.toJson());
        json.put("account", account.toJson());
        return json;
    }







}
