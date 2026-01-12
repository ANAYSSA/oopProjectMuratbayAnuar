package anuarproject.oop1;

public enum Genre {
    ACTION("Боевик"),
    COMEDY("Комедия"),
    DRAMA("Драма"),
    HORROR("Ужасы"),
    FANTASY("Фэнтези"),
    ANIMATION("Анимация"),
    THRILLER("Триллер"),
    ROMANCE("Романтика");

    private String rusName;

    Genre(String rusName) {
        this.rusName = rusName;
    }

    public String getRussianName() {
        return rusName;
    }

    @Override
    public String toString() {
        return rusName;
    }
}