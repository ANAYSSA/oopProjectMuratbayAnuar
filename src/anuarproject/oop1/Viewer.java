package anuarproject.oop1;

public class Viewer {
    private String name;
    private double balance;
    public Viewer(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }
    public String getName() { return name; }
    public double getBalance() { return balance; }
    public boolean buyTicket(Film film) {
        if (balance >= film.getPrice()) {
            balance -= film.getPrice();
            return true;
        }
        return false;
    }
    @Override
    public String toString() {
        return "anuarproject.oop1.Viewer: " + name + ", Balance: " + balance;
    }
}