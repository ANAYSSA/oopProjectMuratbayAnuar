package anuarproject.oop1;
import java.sql.SQLException;
import java.util.Scanner;
public class Main {
public static void main(String[] args)throws SQLException, ClassNotFoundException {
    Scanner scanner = new Scanner(System.in);
    DBManager db = new DBManager();
    Cinema cinema = new Cinema("Anuar Cinema", 50);

    db.getAllFilms().forEach(cinema::addMovie);

    while (true) {
        System.out.println("\n--- WELCOME TO CINEMA SYSTEM ---");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("0. Exit");
        System.out.print("Choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            System.out.print("Username: ");
            String u = scanner.nextLine();
            System.out.print("Password: ");
            String p = scanner.nextLine();

            User user = db.login(u, p);
            if (user instanceof Admin) {
                Admin admin = new Admin(user.getId(), u, p, cinema, scanner);
                admin.showMenu();
            } else if (user instanceof Viewer) {
                ViewerInterface vi = new ViewerInterface(cinema, scanner, (Viewer) user);
                vi.showMenu();
            } else {
                System.out.println("Wrong login or password!");
            }
        } else if (choice == 2) {
            System.out.print("New Username: ");
            String u = scanner.nextLine();
            System.out.print("New Password: ");
            String p = scanner.nextLine();
            System.out.print("Full Name: ");
            String fn = scanner.nextLine();
            System.out.print("Start Balance: ");
            double b = scanner.nextDouble();

            db.registerViewer(u, p, fn, b);
        } else if (choice == 0) break;
    }
}}