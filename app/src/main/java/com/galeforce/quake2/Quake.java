package com.galeforce.quake2;

public class Quake {
    public String title;
    public String url;

    Quake(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public String toString() {
        return this.title + "\r\n" + this.url;
    }
}
