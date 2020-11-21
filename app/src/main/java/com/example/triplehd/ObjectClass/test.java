package com.example.triplehd.ObjectClass;

public class test {
    private Phim movie_1;
    private Phim movie_2;
    private Phim movie_3;
    private Phim movie_4;

    public test(Phim movie_1, Phim movie_2, Phim movie_3, Phim movie_4) {
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
}
