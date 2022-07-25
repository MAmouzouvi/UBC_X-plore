package ui;

import model.*;


import java.util.Scanner;

public class StudentCheckingSystem {

    //offered Courses
    private Course compScience;
    private Course math;
    private Course physics;
    private Course statistics;
    private Course english;

    private CourseRoom offeredCourses;


    //Fees
    private HousingFees housingfees;
    private RecreationFees recreationFees;
    private Student theStudent;
    private Scanner input1;
    private Scanner input2;

    public StudentCheckingSystem() {
        input1 = new Scanner(System.in);
        input2 = new Scanner(System.in);

        runSystem();
    }

    //MODIFIES: this
    //EFFECTS: analyses and works on user inputs
    private void runSystem() {

        boolean keepGoing = true;
        String command;

        initialize();

        while (keepGoing) {
            displayMenu();
            command = input1.next();
            command = command.toLowerCase();

            if (command.equals("quit")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nGoodbye " + theStudent.getStudentName() + " !");
    }

    //MODIFIES :this
    //EFFECTS: initializes a student's system
    private void initialize() {
        theStudent = new Student(8653, "Makafui Amouzouvi", 50000);
        input1 = new Scanner(System.in);
        input1.useDelimiter("\n");

        //COURSES
        compScience = new Course("Computer Science", 5000);
        math = new Course("Mathematics", 6000);
        physics = new Course("Physics", 2000);
        statistics = new Course("Statistics", 7000);
        english = new Course("English", 4000);

        offeredCourses = new CourseRoom("offered Courses");
        offeredCourses.addCourse(compScience);
        offeredCourses.addCourse(math);
        offeredCourses.addCourse(physics);
        offeredCourses.addCourse(statistics);
        offeredCourses.addCourse(english);

        housingfees = new HousingFees("Housing", 10000);
        recreationFees = new RecreationFees("recreation",1000);


    }

    //MODIFIES: this
    //EFFECTS: show all menu options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\t1 -> View my courses");
        System.out.println("\t2 -> Register for a course");
        System.out.println("\t3 -> Deregister from a course");
        System.out.println("\t4 -> Check Balance");
        System.out.println("\t5 -> Pay student fees");
        System.out.println("\tquit -> quit");
    }

    //MODIFIES: this
    //EFFECTS: processes commands from user
    private void processCommand(String command) {
        if (command.equals("1")) {
            viewCourses();
        } else if (command.equals("2")) {
            registerForCourse();
        } else if (command.equals("3")) {
            deregisterFromCourse();
        } else if (command.equals("4")) {
            checkBalance();
        } else if (command.equals("4")) {
            payStudentFees();
        } else {
            System.out.println("Selection not valid...");
        }
    }


    //EFFECTS: print the list of all the courses the student is registered in
    private void viewCourses() {
        if (theStudent.getStudentCourses().size() == 0) {
            System.out.println("You are not registered in any course yet!");
        } else {
            for (Course course : theStudent.getStudentCourses()) {
                int index = theStudent.getStudentCourses().indexOf(course) + 1;
                System.out.println(index + ": " + course.getCourseName());

            }
        }

        displayMenu2();

    }


    private void displayMenu2() {

        boolean keepGoing = true;
        String command;

        input2 = new Scanner(System.in);
        input2.useDelimiter("\n");
        while (keepGoing) {
            displayMenu();
            command = input2.next();
            if (command.equals("quit")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("Bye " + theStudent.getStudentName() + " !");
    }


    //EFFECTS: print the list of all the courses the student is registered in
    private void registerForCourse() {
        courseSelected().registerStudent(theStudent);
        Scanner userInput = new Scanner(System.in);
        displayMenu2();
    }

    private Course courseSelected() {
        System.out.println("Please select the course you want to register for : \n");
        int index = 1;
        for (Course course : offeredCourses.getCourseList()) {
            System.out.println(index + ": " + course.getCourseName());
            System.out.println("Price: " + course.getCourseCost() + " $ \n");

            index++;

        }
        int selection = input1.nextInt();
        return offeredCourses.getCourseList().get(selection - 1);
    }


    private void deregisterFromCourse() {
        courseSelected().deregisterStudent(theStudent);
        Scanner userInput = new Scanner(System.in);
        displayMenu2();
    }

    private void payStudentFees() {
        System.out.println("paid!");
    }

    private void checkBalance() {
        System.out.println("Your account balance is : " + theStudent.getAccountBalance() + " $ !");
    }


}
