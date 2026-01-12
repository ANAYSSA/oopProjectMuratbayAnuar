package anuarproject;

import java.util.Objects;
public abstract class Media {
    protected String title;
    public Media(String title) {
        this.title = title;
    }
    public String getTitle() { return title; }
    public abstract void showDetail();
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Media media = (Media) obj;
        return Objects.equals(title, media.title);
    }
    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}