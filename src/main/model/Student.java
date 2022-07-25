package model;


import java.util.LinkedList;
import java.util.List;

//Represents a student with an id, a name and a list of courses they are registered in
public class Student {
    private int id;
    private String name;
    protected CourseRoom courses;
    private CourseRoom workLists;
    protected Account account;

    public Student(int id, String name, int initialBalance) {
        this.id = id;
        this.name = name;
        this.courses = new CourseRoom("My Courses");
        this.account = new Account(initialBalance);
        this.workLists = new CourseRoom("my CourseLists");
    }

    //MODIFIES: this
    //EFFECTS: if student is not in registered course,
    // - register them
    // - print success message
    // - return true, return false otherwise
    public boolean registerToCourse(Course course) {
        if (!courses.contains(course)) {
            if (this.account.balance >= course.cost) {
                this.account.payFee(course.cost);
                this.courses.addCourse(course);
                course.students.add(this);
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
        if (courses.contains(course)) {
            this.courses.removeCourse(course);
            course.students.remove(this);
            this.account.balance += course.cost;
            System.out.println("Student " + this.name
                    + " has been successfully removed from the course");
            return true;
        } else {
            System.out.println("Student is not registered in this course");
            return false;
        }

    }


    public String getStudentName() {
        return this.name;
    }

    public int getStudentId() {
        return this.id;
    }

//    public Account getAccount() {
    //       return this.account;
//}

    public List<Course> getStudentCourses() {
        return this.courses.getCourseList();
    }

    public int getAccountBalance() {
        return this.account.balance;
    }

}
