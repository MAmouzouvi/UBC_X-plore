package model;


import java.util.List;

//Represents a student with an id, a name, a list of courses they are registered in
//and a finances account
public class Student {
    private int id;
    private String name;
    protected CourseRoom courses;
    protected MaintenanceRequestRoom requests;
    protected Account account;

    //REQUIRES: initialBalance >= 0
    // EFFECTS: constructs a student with an id, a name, an initial balance
    // and an empty list of registered courses
    public Student(int id, String name, int initialBalance) {
        this.id = id;
        this.name = name;
        this.courses = new CourseRoom("My Courses");
        this.account = new Account(initialBalance);
        this.requests = new MaintenanceRequestRoom("My Requests");

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

    //EFFECTS: return the name of the student (this)
    public String getStudentName() {
        return this.name;
    }

    //EFFECTS: return the id of the student (this)
    public int getStudentId() {
        return this.id;
    }

    //EFFECTS: return the Account of the student (this)
    public Account getAccount() {
        return this.account;
    }

    //EFFECTS: return the list of courses the student is registered in
    public List<Course> getStudentCourses() {
        return this.courses.getCourseList();
    }

    //EFFECTS: return the student (this)'s account balance
    public int getAccountBalance() {
        return this.account.balance;
    }

    public MaintenanceRequestRoom getRequestRoom() {
        return this.requests;
    }

}
