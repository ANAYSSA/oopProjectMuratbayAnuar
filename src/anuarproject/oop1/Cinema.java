package anuarproject.oop1;

import java.util.*;

public class Cinema {
    private String name;
    private int seats;
    private List<Film> films = new ArrayList<>();

    public Cinema(String name, int seats) {
        this.name = name;
        this.seats = seats;
    }

    public void addMovie(Film film) {
        films.add(film);
    }

    public void findFilm(String title) {
        for (Film f : films) {
            if (f.getTitle().equalsIgnoreCase(title)) {
                System.out.println("Founded: " + f);
                return;
            }
        }
        System.out.println("Film not found");
    }

    public void sortByPrice() {
        films.sort(Comparator.comparingDouble(Film::getPrice));
        System.out.println("List sorted by price");
    }

    public void showCheapFilms(double maxPrice) {
        System.out.println("Films до " + maxPrice + ":");
        for (Film f : films) {
            if (f.getPrice() <= maxPrice) {
                System.out.println(f);
            }
        }
    }

    public void reserve(Viewer viewer, Film film) {
        if (seats > 0 && viewer.buyTicket(film)) {
            seats--;
            System.out.println(viewer.getName() + " bought ticket to " + film.getTitle());
        } else {
            System.out.println("Error Resorvation to  " + viewer.getName());
        }
    }

    public void showAllFilms() {
        films.forEach(System.out::println);
    }


    public String getName() {
        return name;
    }

    public Film getFilmByTitle(String title) {
        for (Film f : films) {
            if (f.getTitle().equalsIgnoreCase(title)) {
                return f;
            }
        }
        return null;
    }

    public void changeFilmPrice(String title, double newPrice) {
        Film film = getFilmByTitle(title);
        if (film != null) {
            film.setPrice(newPrice);
            System.out.println("Film price '" + title + "' changed to " + newPrice + " тг");
        } else {
            System.out.println("Film not found!");
        }
    }


    public void showFilmsByGenre(Genre genre) {
        System.out.println("\nFilm in Genre " + genre + ":");
        boolean found = false;
        for (Film f : films) {
            if (f.getGenre() == genre) {
                f.showDetail();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No Film with this Genre");
        }
    }
}