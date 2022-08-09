package ui.gui;

import model.*;
import model.exceptions.NegativeAmountException;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

//Represent a student services centre website
public class DemoStudentServicesCentre extends JFrame {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;
    private static final String JSON_STUDENT_ROOM = "./data/studentRoom.json";
    private final JPanel leftMenuPanel;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;


    private JPanel changingPanel;
    private JLabel upLabel;
    private JPanel mainPanel;

    protected Student theStudent;
    private int balance;
    private CourseRoom offeredCourses;
    private List<MaintenanceRequest> requests;

    //offered Courses
    private Course compScience;
    private Course math;
    private Course physics;
    private Course statistics;
    private Course english;


    //fees
    private static int RECREATION_FEE = 1000;
    private static int HOUSING_FEE = 10000;
    private List<Integer> paidFees;

    private JTable coursesTable;


    private DefaultTableModel defaultTable;
    private JTextField depositAmountField;
    private JComboBox selectionBox;
    private List<Course> courses;

    //EFFECTS: create an instance of this website
    public DemoStudentServicesCentre() throws NegativeAmountException {
        super("Student Services App");
        theStudent = new Student("Makafui Amouzouvi");
        upLabel = new JLabel("Welcome " + theStudent.getStudentName());
        upLabel.setBounds(400, 0, 200, 1000);
        upLabel.setForeground(Color.WHITE);
        upLabel.setFont(new Font("Times", Font.ITALIC, 28));

        //panel can be course room panel or maintenance requests room panel
        changingPanel = new JPanel();
        initStudentCourseRoomPanel(changingPanel);

        //up panel
        JPanel upPanel = new JPanel();
        initUpPanel(upPanel,upLabel);

        //Main Panel
        mainPanel = new JPanel();
        initMainPanel(mainPanel);
        //Left Menu Panel
        leftMenuPanel = new JPanel();
        initLeftMenuPanel(leftMenuPanel);
        initSettings();
        initFields();
        pack();
        setVisible(true);
    }


    //MODIFIES: this
    // EFFECTS: Help initialize the up Panel on the Frame (Frame with the student name
    //and image)
    private void initUpPanel(JPanel upPanel, JLabel upLabel) {

        //insert icon which is student picture
        JLabel picture = new JLabel();
        picture.setSize(new Dimension(100,100));
        ImageIcon img = new ImageIcon("/Users/makafuiamouzouvi/project_d0q3f_/src/main/ui/gui/student.jpg");
        Image img1 = img.getImage();
        Image imgScale = img1.getScaledInstance(picture.getWidth(),picture.getHeight(),Image.SCALE_SMOOTH);
        ImageIcon scaledImage = new ImageIcon(imgScale);
        picture.setIcon(scaledImage);

        upPanel.setBounds(0, 0, 1500, 100);
        upPanel.setBackground(new Color(0,25,51));
        upPanel.setPreferredSize(new Dimension(WIDTH, 100));
        upPanel.add(picture);
        upPanel.add(upLabel);
        add(upPanel, BorderLayout.NORTH);
    }


    //MODIFIES: this
    // EFFECTS: Help initialize the main Panel on the Frame. Panel where are displayed the student activities
    private void initMainPanel(JPanel mainPanel) {
        mainPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(changingPanel, BorderLayout.CENTER);
        add(mainPanel);
    }




    //MODIFIES: this
    // EFFECTS: Help initialise the student fields and courses
    private void initFields() throws NegativeAmountException {

        requests = theStudent.getRequestRoom().getRequests();
        balance = theStudent.getAccountBalance();


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
        courses = theStudent.getStudentCourses();

        depositAmountField = new JTextField();

        jsonWriter = new JsonWriter(JSON_STUDENT_ROOM);
        jsonReader = new JsonReader(JSON_STUDENT_ROOM);
    }

    //MODIFIES: this
    // EFFECTS: Help initialize how the frame is displayed on the screen of the desktop (center)
    private void initSettings() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);

        //Place frame in the centre of the desktop
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screen.width - getWidth()) / 2, (screen.height - getHeight()) / 2);

    }


    //MODIFIES: this
    // EFFECTS: Help initialize the course room panel on the frame. The course room panel is the panel where the
    // registered classes are displayed
    private void initStudentCourseRoomPanel(JPanel panel) {
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        panel.setBackground(new Color(51, 0, 102));
        panel.setLayout(new BorderLayout());
        String[] header = new String[]{"Course", "Price"};
        coursesTable = new JTable();
        coursesTable.setRowHeight(40);
        defaultTable = new DefaultTableModel(header, 0);
        coursesTable.setModel(defaultTable);
        coursesTable.setFont(new Font("Times", Font.BOLD, 15));
        coursesTable.setForeground(Color.WHITE);
        coursesTable.setBackground(new Color(51, 0, 102));
        coursesTable.getTableHeader().setOpaque(false);
        coursesTable.setOpaque(false);
        JScrollPane pane = new JScrollPane(coursesTable);
        pane.setBackground(new Color(51, 0, 102));
        panel.add(pane, BorderLayout.CENTER);
        pane.setVisible(true);

        panel.setVisible(true);

    }

    //MODIFIES: this
    // EFFECTS: Help initialize the maintenance request room panel on the frame. The maintenance request room panel
    // is the panel where the submitted maintenance requests are displayed
    private void initMaintenanceRequestsRoom(JPanel panel) {
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        panel.setBackground(new Color(51, 0, 102));
        panel.setLayout(new BorderLayout());
        String[] header = new String[]{"Request Name", "Problem"};
        coursesTable = new JTable();
        coursesTable.setRowHeight(40);
        defaultTable = new DefaultTableModel(header, 0);
        coursesTable.setModel(defaultTable);
        coursesTable.getTableHeader().setOpaque(false);
        coursesTable.setOpaque(false);
        coursesTable.setFont(new Font("Times", Font.BOLD, 15));
        coursesTable.setForeground(Color.WHITE);
        coursesTable.setBackground(new Color(51, 0, 102));
        JScrollPane pane = new JScrollPane(coursesTable);
        pane.setBackground(new Color(51, 0, 102));
        panel.add(pane, BorderLayout.CENTER);
        pane.setVisible(true);

        panel.setVisible(true);
    }

    //MODIFIES: this
    // EFFECTS: Help initialize the left menu bar on the frame.

    private void initLeftMenuPanel(JPanel panel) {
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(270, HEIGHT));

        panel.setBackground(Color.BLACK);
        add(panel, BorderLayout.WEST);
        panel.setLayout(new FlowLayout(12, 10, 27));
        panel.add(initButton(new JButton(new ViewCoursesButton())));
        panel.add(initButton(new JButton(new RegisterForCourseButton())));
        panel.add(initButton(new JButton(new DeregisterFromCourseButton())));
        panel.add(initButton(new JButton(new ViewMaintenanceRequestsButton())));
        panel.add(initButton(new JButton(new SubmitMaintenanceRequestButton())));
        panel.add(initButton(new JButton(new DeleteMaintenanceRequestButton())));
        panel.add(initButton(new JButton(new SaveStudentRoomButton())));
        panel.add(initButton(new JButton(new LoadStudentRoomButton())));
        panel.add(initButton(new JButton(new PayStudentFeesButton())));
        panel.add(initButton(new JButton(new CheckBalanceButton())));
        panel.add(initButton(new JButton(new DepositButton())));
        panel.add(initButton(new JButton(new QuitButton())));
        setVisible(true);
        panel.add(Box.createVerticalStrut(20));
    }

    //MODIFIES: this
    // EFFECTS: Help customize each button

    private Component initButton(JButton button) {
        button.setBorderPainted(false);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Copperplate", Font.BOLD, 18));
        return button;
    }


    /**
     * Helper to update the course room Panel anytime the student register or deregister from a course
     */

    private void updateCoursesRoom() {
        if (theStudent.getStudentCourses().size() == 0) {
            JFrame parent = new JFrame();
            JOptionPane.showMessageDialog(parent, "You are not registered in any course yet !!");
        } else {

            defaultTable.setRowCount(0);
            for (Course course : theStudent.getStudentCourses()) {
                Object[] obj = {course.getCourseName(),
                        course.getCourseCost()};
                defaultTable.addRow(obj);
            }
        }

    }

    /**
     * Helper to update the maintenance requests room Panel anytime a request is submitted or deleted
     */

    private void updateMaintenanceRequestRoom() {
        if (theStudent.getRequestRoom().getRequests().size() == 0) {
            JFrame parent = new JFrame();
            JOptionPane.showMessageDialog(parent, "No request has been submitted yet !");
        } else {
            defaultTable.setRowCount(0);
            for (MaintenanceRequest request : theStudent.getRequestRoom().getRequests()) {
                Object[] obj = {request.getTitle(),
                        request.getProblem()};
                defaultTable.addRow(obj);
            }
        }

    }

    /**
     * Pop-up to select a Course
     */

    private Course selectCourse() {
        selectionBox = new JComboBox();
        Course[] comboBoxData = new Course[offeredCourses.getCourseRoomSize()];

        for (Course course : offeredCourses.getCourses()) {
            selectionBox.addItem(course.getCourseName());
        }

        int index = JOptionPane.showConfirmDialog(null, selectionBox,
                "Select Course", JOptionPane.OK_CANCEL_OPTION);

        if (index == JOptionPane.OK_OPTION) {
            int selectedIndex = selectionBox.getSelectedIndex();
            return offeredCourses.getCourses().get(selectedIndex);
        }
        return null;

    }
    /**
     * Pop-up to select a Request
     */

    private MaintenanceRequest selectRequest() {
        selectionBox = new JComboBox();
        MaintenanceRequest[] comboBoxData = new MaintenanceRequest[requests.size()];

        for (MaintenanceRequest request : theStudent.getRequestRoom().getRequests()) {
            selectionBox.addItem(request.getTitle());
        }

        int index = JOptionPane.showConfirmDialog(null, selectionBox,
                "Select Course", JOptionPane.OK_CANCEL_OPTION);

        if (index == JOptionPane.OK_OPTION) {
            int selectedIndex = selectionBox.getSelectedIndex();
            return theStudent.getRequestRoom().getRequests().get(selectedIndex);
        }
        return null;
    }


   //MODIFIES: this
    //EFFECTS: save student status to file
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

    //MODIFIES: this
    //EFFECTS: save student status to file
    private void loadStudentCheckingSystemStatus() {
        try {
            theStudent = jsonReader.read();
            System.out.println("Loaded " + theStudent.getStudentName() + " from " + JSON_STUDENT_ROOM);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STUDENT_ROOM);
        }
    }


    //Represent the view courses button
    private class ViewCoursesButton extends AbstractAction {

        ViewCoursesButton() {
            super("View Courses");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (theStudent.getStudentCourses().size() == 0) {
                JFrame popUpMessage = new JFrame();
                JOptionPane.showMessageDialog(popUpMessage, "You are not registered in any course yet !!");
            } else {
                changingPanel.removeAll();
                initStudentCourseRoomPanel(changingPanel);
                repaint();
                revalidate();
                setVisible(true);
                updateCoursesRoom();
            }

        }
    }

    //Represent the register for course button
    private class RegisterForCourseButton extends AbstractAction {
        RegisterForCourseButton() {
            super("Register for Course");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Course selectedCourse = selectCourse();
            if (!(selectedCourse == null)) {
                if (theStudent.getStudentCourses().contains(selectedCourse)) {
                    JFrame popUpMessage = new JFrame();
                    JOptionPane.showMessageDialog(popUpMessage, "You are already registered in this course !");
                } else if (theStudent.getAccountBalance() < selectedCourse.getCourseCost()) {
                    JFrame popUpMessage = new JFrame();
                    JOptionPane.showMessageDialog(popUpMessage,
                            "Insufficient Balance, Please Recharge your account !");

                } else {
                    theStudent.registerToCourse(selectedCourse);
                    updateCoursesRoom();
                }
            }

        }
    }

    //Represent the de-register for course button
    private class DeregisterFromCourseButton extends AbstractAction {
        DeregisterFromCourseButton() {
            super("Deregister from Course");
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            Course selectedCourse = selectCourse();
            if (!(selectedCourse == null)) {
                if (!theStudent.getStudentCourses().contains(selectedCourse)) {
                    JFrame popUpMessage = new JFrame();
                    JOptionPane.showMessageDialog(popUpMessage, "You are not registered in this Course !");
                } else {
                    theStudent.deregisterFromCourse(selectedCourse);
                    updateCoursesRoom();
                }
            }

        }
    }

    //Represent the check balance button
    private class CheckBalanceButton extends AbstractAction {

        CheckBalanceButton() {
            super("Check Balance");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame popUpMessage = new JFrame();
            JOptionPane.showMessageDialog(popUpMessage, "Your account Balance is "
                    + theStudent.getAccountBalance() + " $");

        }
    }


    //Represent the pay Student fees button
    private class PayStudentFeesButton extends AbstractAction {
        PayStudentFeesButton() {
            super("Pay Student Fees");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (paidFees.size() == 0) {
                theStudent.getAccount().payFee(RECREATION_FEE);
                theStudent.getAccount().payFee(HOUSING_FEE);
                paidFees.add(RECREATION_FEE);
                paidFees.add(HOUSING_FEE);
                JFrame popUpMessage = new JFrame();
                JOptionPane.showMessageDialog(popUpMessage,
                        "Recreation and Housing fees have successfully been paid !");
            } else {
                JFrame popUpMessage = new JFrame();
                JOptionPane.showMessageDialog(popUpMessage,
                        "Your Student Fees have previously been paid! You have no pending Student Fees!");
            }
        }
    }

    //Represent the Deposit button
    private class DepositButton extends AbstractAction {
        DepositButton() {
            super("Deposit to account");
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            Object[] field = {
                    "Enter Amount to deposit in dollars : ", depositAmountField

            };

            int answer = JOptionPane.showConfirmDialog(null, field, "Store Information",
                    JOptionPane.OK_CANCEL_OPTION);
            if (answer == JOptionPane.OK_OPTION) {
                int amount = Integer.parseInt(depositAmountField.getText());
                try {
                    theStudent.getAccount().deposit(amount);
                } catch (NegativeAmountException ex) {
                    System.out.println("Negative Amount");

                }
                JFrame popUpMessage = new JFrame();
                JOptionPane.showMessageDialog(popUpMessage, "The amount of  "
                        + amount + " $ has been successfully deposited into your account. Your balance is now "
                        + theStudent.getAccount().getBalance() + " $ !");
                depositAmountField.requestFocus();
                depositAmountField.setText("");
            }
        }
    }


    //Represent the view maintenance requests button
    private class ViewMaintenanceRequestsButton extends AbstractAction {
        ViewMaintenanceRequestsButton() {
            super("Maintenance Requests");
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            if (theStudent.getRequestRoom().getRequests().size() == 0) {
                JFrame popUpMessage = new JFrame();
                JOptionPane.showMessageDialog(popUpMessage,
                        "You have not submitted any maintenance request yet !");
            } else {
                changingPanel.removeAll();
                initMaintenanceRequestsRoom(changingPanel);
                repaint();
                revalidate();
                setVisible(true);
                updateMaintenanceRequestRoom();
            }
        }
    }



    //Represent submit a request button
    private class SubmitMaintenanceRequestButton extends AbstractAction {
        SubmitMaintenanceRequestButton() {
            super("Submit Request");
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            JTextField requestTitle = new JTextField();
            JTextField requestProblem = new JTextField();
            Object[] fields = {"Request title ", requestTitle, "Describe the issue here ", requestProblem};

            int answer = JOptionPane.showConfirmDialog(null, fields, "Request Information",
                    JOptionPane.OK_CANCEL_OPTION);
            if (answer == JOptionPane.OK_OPTION) {
                String requestTitleInput = requestTitle.getText();
                String problemDescriptionInput = requestProblem.getText();

                MaintenanceRequest newRequest = new MaintenanceRequest(requestTitleInput, problemDescriptionInput);

                if (!theStudent.getRequestRoom().getRequests().contains(newRequest)) {
                    theStudent.getRequestRoom().getRequests().add(newRequest);
                    changingPanel.removeAll();
                    initMaintenanceRequestsRoom(changingPanel);
                    repaint();
                    revalidate();
                    setVisible(true);
                    updateMaintenanceRequestRoom();

                } else {
                    JFrame popUpMessage = new JFrame();
                    JOptionPane.showMessageDialog(popUpMessage,
                            "This request has already been submitted!");
                }
            }
        }
    }


    //Represent delete a request button
    private class DeleteMaintenanceRequestButton extends AbstractAction {
        DeleteMaintenanceRequestButton() {
            super("Delete Request");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (requests.size() == 0) {
                JFrame popUpMessage = new JFrame();
                JOptionPane.showMessageDialog(popUpMessage, "There is no submitted request yet!");
            } else {
                MaintenanceRequest selectedRequest = selectRequest();
                if (!(selectedRequest == null)) {
                    theStudent.getRequestRoom().deleteRequest(selectedRequest);
                    changingPanel.removeAll();
                    initMaintenanceRequestsRoom(changingPanel);
                    repaint();
                    revalidate();
                    setVisible(true);
                    updateMaintenanceRequestRoom();
                }
            }

        }
    }

    //Represent Save Student Status Button
    private class SaveStudentRoomButton extends AbstractAction {

        SaveStudentRoomButton() {
            super("Save Student Status");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (theStudent.getStudentCourses().size() == 0) {
                JFrame parent = new JFrame();
                JOptionPane.showMessageDialog(parent,
                        "You have no data to save!!");
            } else {
                saveStudentCheckingSystemStatus();
                //updateCoursesRoom();
                JFrame parent = new JFrame();
                JOptionPane.showMessageDialog(parent,
                        "Student Services Centre Status has been saved to file!!");
            }
        }
    }


    //Represent load student status button
    private class LoadStudentRoomButton extends AbstractAction {

        LoadStudentRoomButton() {
            super("Load Student Status");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            loadStudentCheckingSystemStatus();
            updateCoursesRoom();
            updateCoursesRoom();
            JFrame parent = new JFrame();
            JOptionPane.showMessageDialog(parent, "Student Status was successfully loaded!");
        }
    }


    //Represent the quit website button
    private class QuitButton extends AbstractAction {
        QuitButton() {
            super("Quit");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
        }
    }

}


