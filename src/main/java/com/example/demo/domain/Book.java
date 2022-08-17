package com.example.demo.domain;

public class Book {
    private int book_id;
    private String name;
    private String synopsis;
    private int category_id;

    public Book(int book_id, String name, String synopsis, int category_id) {
        this.book_id = book_id;
        this.name = name;
        this.synopsis = synopsis;
        this.category_id = category_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }
}
