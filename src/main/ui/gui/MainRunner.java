package ui.gui;

import model.Exceptions.NegativeAmountException;

public class MainRunner {

    //Run the App
    public static void main(String[] args) {
        try {
            new DemoStudentServicesCentre();
        } catch (NegativeAmountException e) {
            //fail
        }
    }
}