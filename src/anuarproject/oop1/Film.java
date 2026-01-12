package anuarproject.oop1;

public class Film extends Media {
    private double price;
    private Genre genre;


    public Film(String title, double price) {
        super(title);
        this.price = price;
        this.genre = Genre.DRAMA;
    }


    public Film(String title, double price, Genre genre) {
        super(title);
        this.price = price;
        this.genre = genre;
    }

    public double getPrice() {
        return price;
    }



    public void setPrice(double price) {
        this.price = price;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }


    @Override
    public void showDetail() {
        System.out.println("Kino: " + title + " | anuarproject.oop1.Genre: " + genre + " | Price: " + price);
    }

    @Override
    public String toString() {
        return "anuarproject.oop1.Film: '" + title + "' [" + genre + "], Price: " + price;
    }
}