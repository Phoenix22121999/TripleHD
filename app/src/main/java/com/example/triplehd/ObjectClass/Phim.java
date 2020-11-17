package com.example.triplehd.ObjectClass;

public class Phim {
    private int id;
    private String link;
    private String title;
    private int genre;

    public Phim(int id, String link, String title, int genre) {
        this.id = id;
        this.link = link;
        this.title = title;
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return "Phim{" +
                "id=" + id +
                ", link='" + link + '\'' +
                ", title='" + title + '\'' +
                ", genre=" + genre +
                '}';
    }

    public void setGenre(int genre) {
        this.genre = genre;
    }
}
