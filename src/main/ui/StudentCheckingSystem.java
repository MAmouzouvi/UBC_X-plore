package ui;

import model.*;


import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


//Represents a student services checking system
public class StudentCheckingSystem {

    private static int RECREATION_FEE = 1000;
    private static int HOUSING_FEE = 10000;
    private List<Integer> paidFees;

    //offered Courses
    private Course compScience;
    private Course math;
    private Course physics;
    private Course statistics;
    private Course english;

    private CourseRoom offeredCourses;


    private Student theStudent;
    private Scanner input1;
    private Scanner input2;

    //EFFECTS: launch the class
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
    //EFFECTS: initializes a student's system details
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

        paidFees = new LinkedList<>();


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
        System.out.println("\t6 -> Deposit ");
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
        } else if (command.equals("5")) {
            payStudentFees();
        } else if (command.equals("6")) {
            depositMoney();
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

    //EFFECTS: display menu
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


    //EFFECTS: register student into the selected course
    private void registerForCourse() {
        courseSelected().registerStudent(theStudent);
        Scanner userInput = new Scanner(System.in);
        displayMenu2();
    }

    //EFFECTS: process the user course selection command
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


    //EFFECTS: register student into the selected course
    private void deregisterFromCourse() {
        courseSelected().deregisterStudent(theStudent);
        Scanner userInput = new Scanner(System.in);
        displayMenu2();
    }

    //EFFECTS: pay student's fees and subtract amount from balance
    private void payStudentFees() {
        if (paidFees.size() == 0) {
            theStudent.getAccount().payFee(RECREATION_FEE);
            theStudent.getAccount().payFee(HOUSING_FEE);
            paidFees.add(RECREATION_FEE);
            paidFees.add(HOUSING_FEE);
            System.out.println("Recreation and Housing fees have been paid !");

        } else {
            System.out.println("There is no fees to be paid. Fees have previously been paid !");
        }
    }

    //EFFECTS: print out the balance in student account
    private void checkBalance() {
        System.out.println("Your account balance is : " + theStudent.getAccountBalance() + " $ !");
    }

    //EFFECTS: processes amount entered and deposit it into student account
    private void depositMoney() {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter the amount you would like to deposit into your account($) :");
        int number = userInput.nextInt();
        theStudent.getAccount().deposit(number);
        System.out.println("Amount has been successfully deposited !");
    }


}
