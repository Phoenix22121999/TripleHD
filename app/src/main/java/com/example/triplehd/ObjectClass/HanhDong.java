package com.example.triplehd.ObjectClass;

import java.util.Objects;

public class HanhDong {
    private Phim movie_1;
    private Phim movie_2;
    private Phim movie_3;
    private Phim movie_4;

    public HanhDong(Phim movie_1, Phim movie_2, Phim movie_3, Phim movie_4) {
        this.movie_1 = movie_1;
        this.movie_2 = movie_2;
        this.movie_3 = movie_3;
        this.movie_4 = movie_4;
    }

    public Phim getMovie_1() {
        return movie_1;
    }

    public void setMovie_1(Phim movie_1) {
        this.movie_1 = movie_1;
    }

    public Phim getMovie_2() {
        return movie_2;
    }

    public void setMovie_2(Phim movie_2) {
        this.movie_2 = movie_2;
    }

    public Phim getMovie_3() {
        return movie_3;
    }

    public void setMovie_3(Phim movie_3) {
        this.movie_3 = movie_3;
    }

    public Phim getMovie_4() {
        return movie_4;
    }

    public void setMovie_4(Phim movie_4) {
        this.movie_4 = movie_4;
    }

    @Override
    public String toString() {
        return "HanhDong{" +
                "movie_1=" + movie_1 +
                ", movie_2=" + movie_2 +
                ", movie_3=" + movie_3 +
                ", movie_4=" + movie_4 +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HanhDong hanhDong = (HanhDong) o;
        return Objects.equals(movie_1, hanhDong.movie_1) &&
                Objects.equals(movie_2, hanhDong.movie_2) &&
                Objects.equals(movie_3, hanhDong.movie_3) &&
                Objects.equals(movie_4, hanhDong.movie_4);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movie_1, movie_2, movie_3, movie_4);
    }
}
