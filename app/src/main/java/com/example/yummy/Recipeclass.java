package com.example.yummy;


public class Recipeclass {
    private String name,ingredients,method,note,portion;
    private int thumbnail;


    public Recipeclass() {
    }

    public Recipeclass(String name, String ingredients, String method, String note, String portion, int thumbnail) {
        this.name = name;
        this.ingredients = ingredients;
        this.method = method;
        this.note = note;
        this.portion = portion;
        this.thumbnail = thumbnail;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPortion() {
        return portion;
    }

    public void setPortion(String portion) {
        this.portion = portion;
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
