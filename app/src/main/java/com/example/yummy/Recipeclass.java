package com.example.yummy;

/**
 * Created by Lincoln on 18/05/16.
 */
public class Recipeclass {
    private String name;
//    private int numOfSongs;
    private int thumbnail;

    public Recipeclass() {
    }

    public Recipeclass(String name, int thumbnail) {
        this.name = name;
//        this.numOfSongs = numOfSongs;
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public int getNumOfSongs() {
//        return numOfSongs;
//    }
//
//    public void setNumOfSongs(int numOfSongs) {
//        this.numOfSongs = numOfSongs;
//    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
