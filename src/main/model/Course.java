package model;

import java.util.LinkedList;
import java.util.List;

//Represent a course with a name, a cost and a list of registered Students
public class Course {
    private String name;
    protected int cost;
    protected List<Student> students;


    //REQUIRES: cost > 0;
    // EFFECTS: constructs a course with a name and a cost, and an empty list of registered students
    public Course(String name, int cost) {
        this.name = name;
        this.cost = cost;
        this.students = new LinkedList<>();
    }


    //MODIFIES: this
    //EFFECTS: if student is not registered in the course,
    // adds student to the list of students in the course, print success message and
    // return true, return false otherwise.
    public boolean registerStudent(Student student) {
        if (!students.contains(student)) {
            if (student.account.balance >= this.cost) {
                student.account.payFee(this.cost);
                this.students.add(student);
                student.courses.addCourse(this);
                System.out.println("Student " + student.getStudentName()
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
    // -remove them from the list of registered students,
    // - refund them the amount of the course
    // - print success message
    //  - return true, return false otherwise

    public boolean deregisterStudent(Student student) {
        if (students.contains(student)) {
            this.students.remove(student);
            student.courses.removeCourse(this);
            student.account.balance += this.cost;
            System.out.println("Student " + student.getStudentName()
                    + " has been successfully removed from the course");
            return true;
        } else {
            System.out.println("Student is not registered in this course");
            return false;
        }

    }


    public String getCourseName() {
        return this.name;
    }

    public int getCourseCost() {
        return this.cost;
    }

    public List<Student> getStudents() {
        return this.students;
    }


}