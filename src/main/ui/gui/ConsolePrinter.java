package ui.gui;

import model.EventLog;


import model.Event;

/**
 * Represents a console printer for printing event log to the console.
 */
public class ConsolePrinter  implements LogPrinter {

    /**
     * Constructs an instance of the console printer
     */
    public ConsolePrinter() {
        super();
    }

    @Override
    public void printLog(EventLog el) {

        for (Event next : el) {
            System.out.println(next.toString());
        }

    }

}
