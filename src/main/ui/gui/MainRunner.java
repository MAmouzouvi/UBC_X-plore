package ui.gui;

import model.exceptions.NegativeAmountException;


public class MainRunner {
    private int age;

    //Run the App
    public static void main(String[] args) {
        try {
            new DemoStudentServicesCentre();
        } catch (NegativeAmountException e) {
            //fail
        }
    }

}
