package com.example.triplehd.ObjectClass;

public class myPoster {
    int image;
    String name;

    public myPoster(int image, String name) {
        this.image = image;
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "myPoster{" +
                "image=" + image +
                ", name='" + name + '\'' +
                '}';
    }
}
