package com.example.loginandsignup;

public class Book {
    public Book(String key, String bookName, String bookAuthor) {
        this.key = key;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
    }

    public Book() {

    }
    private String key,bookName,bookAuthor;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }
}
