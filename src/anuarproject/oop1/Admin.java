package anuarproject.oop1;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Admin extends User{

    private Cinema cinema;
    private Scanner scanner;
    DBManager db =  new DBManager();
    public Admin(int id, String username, String password, Cinema cinema, Scanner scanner) {
        super(id, username, password);
        this.cinema = cinema;
        this.scanner = scanner;
    }


    public void showMenu() {
        while (true) {
            System.out.println("\n========== ADMIN MENU ==========");
            System.out.println("1. Add Film");
            System.out.println("2. Show all Films");
            System.out.println("3. Change Film Price");
            System.out.println("4. Sort Film by Price");
            System.out.println("5. Delete film");
            System.out.println("0. Exit to main menu");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addFilm();
                    break;
                case 2:
                    ShowAllFilms();
                    break;
                case 3:
                    changePrice();
                    break;
                case 4:
                    cinema.sortByPrice();
                    ShowAllFilms();
                    break;
                case 5:
                    DeleteFilm();
                case 0:
                    return;
                default:
                    System.out.println("Wrong choice!");
            }
        }
    }

    private void addFilm() {
        System.out.println("Enter Film title:");
        String title = scanner.nextLine();
        System.out.println("Enter Film price:");
        Double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Enter Film genre:");
        System.out.println("ACTION, COMEDY, DRAMA,HORROR,FANTASY,ANIMATION,THRILLER,ROMANCE");
        String Genree = scanner.nextLine().trim().toUpperCase();
        Genre genre = Genre.valueOf(Genree);
        Film film = new Film(title, price,genre);
        try{db.Addfilm(film);
        System.out.println("Film added successfully!");}
        catch(Exception e){System.out.println(e.getMessage());}
    }

    private void changePrice() {
        System.out.print("Enter film title: ");
        String title = scanner.nextLine();

        System.out.print("Enter new Price: ");
        double newPrice = scanner.nextDouble();
        scanner.nextLine();

        try{db.updateFilmPrice(title,newPrice);}
        catch(Exception e){System.out.println(e.getMessage());}
    }


    private void DeleteFilm(){
        System.out.print("Enter film title: ");
        String title = scanner.nextLine();
        try{db.deleteFilm(title);}
        catch(Exception e){System.out.println(e.getMessage());}

    }
    private void ShowAllFilms(){
        System.out.print("All films:");
        try{List<Film> films = db.getAllFilms();
        for (Film film : films) {System.out.println(film);}System.out.println("Showed all Films");}catch(Exception e){System.out.println(e.getMessage());}
    }
    private void filterByGenre() {
        System.out.println("Choose Genre to sort by:");
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
            System.out.println("Wrong number of genres!");
        }
    }
}