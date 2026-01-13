package anuarproject.oop1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        Cinema cinema = new Cinema("Anuar Cinema", 50);


        cinema.addMovie(new Film("Spider-Man: No Way Home", 2500, Genre.ACTION));
        cinema.addMovie(new Film("Minions", 1500, Genre.ANIMATION));
        cinema.addMovie(new Film("Inception", 2000, Genre.THRILLER));
        cinema.addMovie(new Film("The Notebook", 1800, Genre.ROMANCE));
        cinema.addMovie(new Film("IT", 2200, Genre.HORROR));


        Admin admin = new Admin(1, "admin", "1234", cinema, scanner);
        ViewerInterface viewerInterface = new ViewerInterface(cinema, scanner);

        System.out.println("════════════════════════════════════════");
        System.out.println("   Welcome to " + cinema.getName() + "! ");
        System.out.println("════════════════════════════════════════");


        while (true) {
            System.out.println("\n========== MAIN MENU ==========");
            System.out.println("1. Log in as Administrator");
            System.out.println("2. Log in as Viewer");
            System.out.println("3. Exit");
            System.out.print("Select Role: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("\n--- Log in as anuarproject.oop1.Admin ---");
                    admin.showMenu();
                    break;
                case 2:
                    System.out.println("\n--- Log in as anuarproject.oop1.Viewer ---");
                    viewerInterface.showMenu();
                    break;
                case 3:
                    System.out.println("\nThanks!");
                    System.out.println("see you soon!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Wrong choice!");
            }
        }
    }
}