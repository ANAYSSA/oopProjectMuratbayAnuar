package anuarproject.oop1;

public class Viewer extends User{
    private String name;
    private double balance;
    public Viewer(int id , String username, String password,String name, double balance) {
        super(id,username,password);
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
        return "Viewer: " + name + ", Balance: " + balance;
    }
}