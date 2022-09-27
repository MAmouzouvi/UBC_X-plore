package model;

import model.exceptions.NegativeAmountException;
import org.json.JSONObject;
import persistence.Writable;

import java.util.Objects;



//Represent a course with a name, a cost and a list of registered Students
public class Course implements Writable {
    private String courseName;
    protected int cost;
    // protected List<Student> students;


    // EFFECTS: constructs a course with a name and a cost, and an empty list of registered students
    public Course(String name, int cost) throws NegativeAmountException {
        if (cost < 0) {
            throw new NegativeAmountException();
        }
        this.courseName = name;
        this.cost = cost;
        // this.students = new LinkedList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        Course course = (Course) o;
        return Objects.equals(getCourseName(), course.getCourseName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCourseName());
    }

/*

    //MODIFIES: this
    //EFFECTS: if student is not registered in the course,
    // adds student to the list of students in the course, print success message and
    // return true, return false otherwise.
    public boolean registerStudent(Student student) {
        if (!students.contains(student)) {
            if (student.account.balance >= this.cost) {
                student.account.payFee(this.cost);
                this.students.add(student);
                student.courseRoom.addCourse(this);
                System.out.println("Student " + student.getStudentName()
                        + " has been successfully registered into the course !");
                return true;
            } else {
                System.out.println("Insufficient balance !");
                System.out.println("Please recharge your account !");
                return false;
            }
        } else {
            System.out.println("Student is already registered in course");
            return false;
        }
    }

    //MODIFIES: this
    //EFFECTS: if student is registered in course,
    // -remove them from the list of registered students,
    // - refund them the amount of the course
    // - print success message
    //  - return true, return false otherwise

    public boolean deregisterStudent(Student student) {
        if (students.contains(student)) {
            this.students.remove(student);
            student.courseRoom.removeCourse(this);
            student.account.balance += this.cost;
            System.out.println("Student " + student.getStudentName()
                    + " has been successfully removed from the course");
            return true;
        } else {
            System.out.println("Student is not registered in this course");
            return false;
        }

    }

*/

    //EFFECTS: return the course name
    public String getCourseName() {
        return this.courseName;
    }

    //EFFECTS: return the course cost
    public int getCourseCost() {
        return this.cost;
    }
/*
    public List<Student> getStudents() {
        return this.students;
    }*/


    //EFFECTS: write course as a JSON Object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("courseName", courseName);
        json.put("cost", cost);
        //       json.put("students", studentsToJson());
        return json;
    }

}
