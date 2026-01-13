package anuarproject.oop1;

import java.util.Scanner;

public class Admin extends User{

    private Cinema cinema;
    private Scanner scanner;

    public Admin(int id, String username, String password, Cinema cinema, Scanner scanner) {
        super(id, username, password);
        this.cinema = cinema;
        this.scanner = scanner;
    }




    public void showMenu() {
        while (true) {
            System.out.println("\n========== ADMIN MENU ==========");
            System.out.println("1. Add anuarproject.oop1.Film");
            System.out.println("2. Show all Films");
            System.out.println("3. Change anuarproject.oop1.Film Price");
            System.out.println("4. Sort anuarproject.oop1.Film by Price");
            System.out.println("5. Filtr by price");
            System.out.println("6. Filtr by genre");
            System.out.println("0. Exit to main menu");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addFilm();
                    break;
                case 2:
                    cinema.showAllFilms();
                    break;
                case 3:
                    changePrice();
                    break;
                case 4:
                    cinema.sortByPrice();
                    cinema.showAllFilms();
                    break;
                case 5:
                    filterByPrice();
                    break;
                case 6:
                    filterByGenre();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Wrong choice!");
            }
        }
    }

    private void addFilm() {
        System.out.print("Enter film name: ");
        String title = scanner.nextLine();

        System.out.print("Enter Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Choose anuarproject.oop1.Genre:");
        Genre[] genres = Genre.values();
        for (int i = 0; i < genres.length; i++) {
            System.out.println((i + 1) + ". " + genres[i]);
        }
        System.out.print("anuarproject.oop1.Genre name: ");
        int genreChoice = scanner.nextInt() - 1;
        scanner.nextLine();

        if (genreChoice >= 0 && genreChoice < genres.length) {
            Film film = new Film(title, price, genres[genreChoice]);
            cinema.addMovie(film);
            System.out.println("anuarproject.oop1.Film added successfully!");
        } else {
            System.out.println("Wrong number of genres!");
        }
    }

    private void changePrice() {
        System.out.print("Enter film name: ");
        String title = scanner.nextLine();

        System.out.print("Enter new Price: ");
        double newPrice = scanner.nextDouble();
        scanner.nextLine();

        cinema.changeFilmPrice(title, newPrice);
    }

    private void filterByPrice() {
        System.out.print("Enter max anuarproject.oop1.Film: ");
        double maxPrice = scanner.nextDouble();
        scanner.nextLine();
        cinema.showCheapFilms(maxPrice);
    }

    private void filterByGenre() {
        System.out.println("Choose anuarproject.oop1.Genre to sort by:");
        Genre[] genres = Genre.values();
        for (int i = 0; i < genres.length; i++) {
            System.out.println((i + 1) + ". " + genres[i]);
        }
        System.out.print("anuarproject.oop1.Genre number: ");
        int genreChoice = scanner.nextInt() - 1;
        scanner.nextLine();

        if (genreChoice >= 0 && genreChoice < genres.length) {
            cinema.showFilmsByGenre(genres[genreChoice]);
        } else {
            System.out.println("Wrong number of genres!");
        }
    }
}