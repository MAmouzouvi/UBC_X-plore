package ui;

import model.*;
import model.exceptions.NegativeAmountException;
import persistence.JsonReader;
import persistence.JsonWriter;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


//Represents a student services checking system
public class StudentCheckingSystem {
    private static final String JSON_STUDENT_ROOM = "./data/studentRoom.json";
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;

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
        jsonWriter = new JsonWriter(JSON_STUDENT_ROOM);
        jsonReader = new JsonReader(JSON_STUDENT_ROOM);
        try {
            initialize();
        } catch (NegativeAmountException e) {
            System.out.println("Course cost is negative : Illegal amount!");
        }
        try {
            runSystem();
        } catch (NegativeAmountException e) {
            System.out.println("You entered a negative amount");
        }
    }

    //MODIFIES: this
    //EFFECTS: analyses and works on user inputs
    private void runSystem() throws NegativeAmountException {
        boolean running = true;
        String userCommand;
        while (running) {
            userMenu();
            userCommand = input1.next();
            // userCommand = userCommand.toLowerCase();
            if (userCommand.equals("quit")) {
                running = false;
                System.out.println("\nGoodbye " + theStudent.getStudentName() + " !");
            } else {
                processCommand(userCommand);
            }
        }
    }

    //MODIFIES :this
    //EFFECTS: initializes a student's system details
    private void initialize() throws NegativeAmountException {
        theStudent = new Student("Makafui Amouzouvi");
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
    private void userMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\t1 -> View my courses");
        System.out.println("\t2 -> Register for a course");
        System.out.println("\t3 -> Deregister from a course");
        System.out.println("\t4 -> Check Balance");
        System.out.println("\t5 -> Pay student fees");
        System.out.println("\t6 -> Deposit ");
        System.out.println("\t7 -> View Maintenance Requests");
        System.out.println("\t8 -> Submit a Maintenance request");
        System.out.println("\t9 -> Delete a Maintenance request");
        System.out.println("\t10 -> Save student's room status to file");
        System.out.println("\t11 -> Load student's room status from file");
        System.out.println("\tquit -> quit");
    }

    //MODIFIES: this
    //EFFECTS: processes commands from user
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void processCommand(String command) throws NegativeAmountException {
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
        } else if (command.equals("7")) {
            viewMaintenanceRequests();
        } else if (command.equals("8")) {
            submitRequest();
        } else if (command.equals("9")) {
            deleteRequest();
        } else if (command.equals("10")) {
            saveStudentCheckingSystemStatus();
        } else if (command.equals("11")) {
            loadStudentCheckingSystemStatus();
        } else {
            System.out.println("Selection is not valid...");
        }
    }


    //EFFECTS: print the list of all the courses the student is registered in
    private void viewCourses() throws NegativeAmountException {
        if (theStudent.getStudentCourses().size() == 0) {
            System.out.println("You are not registered in any course yet!");
        } else {
            System.out.println("Your courses are :");
            for (Course course : theStudent.getStudentCourses()) {
                int index = theStudent.getStudentCourses().indexOf(course) + 1;

                System.out.println(index + ": " + course.getCourseName());

            }
        }

        runSystem();

    }

    //EFFECTS: display menu
    private void userMenu2() throws NegativeAmountException {

        boolean running = true;
        String userCommand;

        // input2 = new Scanner(System.in);
        // input2.useDelimiter("\n");
        while (running) {
            userMenu();
            userCommand = input2.next();

            if (userCommand.equals("quit")) {
                running = false;
            } else {
                processCommand(userCommand);
            }
        }
        System.out.println("Bye " + theStudent.getStudentName() + " !");
    }


    //EFFECTS: register student into the selected course
    private void registerForCourse() throws NegativeAmountException {

        theStudent.registerToCourse(courseSelected());
        //  courseSelected().registerStudent(theStudent);
        runSystem();
    }

    //EFFECTS: process the user course selection command
    private Course courseSelected() {
        System.out.println("Please select the course you want to register for : \n");
        int index = 1;
        for (Course course : offeredCourses.getCourses()) {
            System.out.println(index + ": " + course.getCourseName());
            System.out.println("Price: " + course.getCourseCost() + " $ \n");

            index++;

        }
        int selection = input1.nextInt();
        return offeredCourses.getCourses().get(selection - 1);
    }


    //EFFECTS: register student into the selected course
    private void deregisterFromCourse() throws NegativeAmountException {
        theStudent.deregisterFromCourse(courseSelected());
        //courseSelected().deregisterStudent(theStudent);
        runSystem();
    }

    //EFFECTS: pay student's fees and subtract amount from balance
    private void payStudentFees() throws NegativeAmountException {
        if (paidFees.size() == 0) {
            theStudent.getAccount().payFee(RECREATION_FEE);
            theStudent.getAccount().payFee(HOUSING_FEE);
            paidFees.add(RECREATION_FEE);
            paidFees.add(HOUSING_FEE);
            System.out.println("Recreation and Housing fees have been paid !");

        } else {
            System.out.println("There is no fees to be paid. Fees have previously been paid !");
        }
        runSystem();
    }

    //EFFECTS: print out the balance in student account
    private void checkBalance() throws NegativeAmountException {
        System.out.println("Your account balance is : " + theStudent.getAccountBalance() + " $ !");
        runSystem();
    }

    //EFFECTS: processes amount entered and deposit it into student account
    private void depositMoney() throws NegativeAmountException {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter the amount you would like to deposit into your account($) :");
        int number = userInput.nextInt();
        theStudent.getAccount().deposit(number);
        System.out.println("Amount has been successfully deposited !");
        runSystem();
    }


    //EFFECTS: print the list of all the maintenance requests submitted by the student
    private void viewMaintenanceRequests() throws NegativeAmountException {
        if (theStudent.getRequestRoom().getRequests().size() == 0) {
            System.out.println("You have not submitted any Maintenance Request Yet!");
        } else {
            System.out.println("Your submitted requests :");
            for (MaintenanceRequest request : theStudent.getRequestRoom().getRequests()) {
                int index = theStudent.getRequestRoom().getRequests().indexOf(request) + 1;
                System.out.println("Request " + index + ": " + request.getTitle());
                System.out.println("Problem Description : " + request.getProblem() + "\n");
            }
        }

        runSystem();
    }

    //EFFECTS: adds a request to the list of submitted requests
    private void submitRequest() throws NegativeAmountException {
        Scanner userInput1 = new Scanner(System.in);
        Scanner userInput2 = new Scanner(System.in);
        System.out.println("Enter a title for your Maintenance Request !");
        String title = userInput1.nextLine();
        System.out.println("Enter the details of your Maintenance Request !");
        String problem = userInput2.nextLine();
        MaintenanceRequest request = new MaintenanceRequest(title, problem);
        theStudent.getRequestRoom().submitRequest(request);
        System.out.println("The request has been successfully submitted !");
        runSystem();

    }


    //EFFECTS: removes a request to the list of submitted requests
    private void deleteRequest() throws NegativeAmountException {
        if (theStudent.getRequestRoom().getRequests().size() == 0) {
            System.out.println("You have not submitted any request yet!");
        } else {
            MaintenanceRequest requestSelected = requestSelected();
            theStudent.getRequestRoom().deleteRequest(requestSelected);

        }
        runSystem();
    }

    //EFFECTS: process the user course selection command
    private MaintenanceRequest requestSelected() {
        System.out.println("Please select the request you want to delete : \n");
        int index = 1;
        for (MaintenanceRequest request : theStudent.getRequestRoom().getRequests()) {
            System.out.println(index + ": " + request.getTitle() + "\n");
            index++;

        }
        int selection = input1.nextInt();
        return theStudent.getRequestRoom().getRequests().get(selection - 1);
    }

    private void saveStudentCheckingSystemStatus() {
        try {
            jsonWriter.open();
            jsonWriter.write(theStudent);
            jsonWriter.close();
            System.out.println("Saved " + theStudent.getStudentName() + "'s services system status to "
                    + JSON_STUDENT_ROOM);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STUDENT_ROOM);
        }
    }


    private void loadStudentCheckingSystemStatus() {
        try {
            theStudent = jsonReader.read();
            System.out.println("Loaded " + theStudent.getStudentName() + " from " + JSON_STUDENT_ROOM);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STUDENT_ROOM);
        }
    }

}
