package anuarproject.oop1;

import java.util.Scanner;

public class ViewerInterface {
    private Cinema cinema;
    private Viewer viewer;
    private Scanner scanner;

    public ViewerInterface(Cinema cinema, Scanner scanner, Viewer viewer) {
        this.cinema = cinema;
        this.scanner = scanner;
        this.viewer = viewer;
    }

    public void showMenu() {

        while (true) {
            System.out.println("\n========== Viewer Menu ==========");
            System.out.println("Balance: " + viewer.getBalance() + " тг");
            System.out.println("1. Show all Films");
            System.out.println("2. Search Film");
            System.out.println("3. Filter by Genre");
            System.out.println("4. Filter by Price");
            System.out.println("5. Reserve Ticket");
            System.out.println("0. Exit to main menu");
            System.out.print("choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    cinema.showAllFilms();
                    break;
                case 2:
                    searchFilm();
                    break;
                case 3:
                    filterByGenre();
                    break;
                case 4:
                    filterByPrice();
                    break;
                case 5:
                    reserveTicket();
                    break;
                case 0:
                    System.out.println("Good bye, " + viewer.getName() + "!");
                    return;
                default:
                    System.out.println("Wrong choice!");
            }
        }
    }

    private void searchFilm() {
        System.out.print("Enter Film name: ");
        String title = scanner.nextLine();
        cinema.findFilm(title);
    }

    private void filterByGenre() {
        System.out.println("Choose a Genre: :");
        Genre[] genres = Genre.values();
        for (int i = 0; i < genres.length; i++) {
            System.out.println((i + 1) + ". " + genres[i]);
        }
        System.out.print("Genre number: ");
        int genreChoice = scanner.nextInt() - 1;
        scanner.nextLine();

        if (genreChoice >= 0 && genreChoice < genres.length) {
            cinema.showFilmsByGenre(genres[genreChoice]);
        } else {
            System.out.println("Wrong genre number!");
        }
    }

    private void filterByPrice() {
        System.out.print("Enter max price: ");
        double maxPrice = scanner.nextDouble();
        scanner.nextLine();
        cinema.showCheapFilms(maxPrice);
    }

    private void reserveTicket() {
        System.out.print("Enter Film name to reserve: ");
        String title = scanner.nextLine();

        Film film = cinema.getFilmByTitle(title);
        if (film != null) {
            cinema.reserve(viewer, film);
        } else {
            System.out.println("Film not found!");
        }
    }
}