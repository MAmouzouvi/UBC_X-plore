package model;

// represents a student financial account. Every amount is in $.
public class Account {
    private float id;
    protected int balance;

    //REQUIRES: initialBalance >= 0
    //EFFECTS: constructs a student financial account with a given
    //initial balance.
    public Account(int initialBalance) {
       // this.id = id;
        this.balance = initialBalance;

    }

    // REQUIRES: amount > 0
    // MODIFIES: this
    // EFFECTS: adds amount to balance of student
    public void deposit(float amount) {
        this.balance += amount;
    }


    // MODIFIES: this
    // EFFECTS: if there is sufficient balance on the account
    //            - subtracts amount from balance
    //            - returns true
    //          otherwise, returns false
    public boolean payFee(float amount) {
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

}
