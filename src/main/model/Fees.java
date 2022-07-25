package model;

public abstract class Fees {
    private String name;
    private int amount;

    public Fees(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }
}
