package com.example.library2;

public class BookModel {
    private int id;
    private String name, author;
    public BookModel(){

    }
    public BookModel(int id, String name, String author) {
        this.id = id;
        this.name = name;
        this.author = author;
    }

    public BookModel(String name, String author) {
        this.name = name;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
