package model;

import model.exceptions.NegativeAmountException;
import org.json.JSONObject;

// represents a student financial account. Every amount is in $.
public class Account {
    protected int balance;

    //EFFECTS: constructs a student financial account with a given
    //initial balance.
    public Account() {
        this.balance = 0;

    }

    // MODIFIES: this
    // EFFECTS: adds amount to balance of student
    public void deposit(int amount) throws NegativeAmountException {
        if (amount < 0) {
            throw new NegativeAmountException();
        }
        this.balance += amount;
    }


    // MODIFIES: this
    // EFFECTS: if there is sufficient balance on the account
    //            - subtracts amount from balance
    //            - returns true
    //          otherwise, returns false
    public boolean payFee(int amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        } else {
            return false;
        }
    }


    // EFFECTS: returns remaining balance on account in dollars
    public int getBalance() {
        return balance;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("balance", balance);
        return json;
    }
}
