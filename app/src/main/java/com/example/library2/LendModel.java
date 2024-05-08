package com.example.library2;

public class LendModel {
    private int id;
    private String member, book;
    private long started, finished;
    public LendModel(){

    }
    public LendModel(int id, String member, String book, long started, long finished) {
        this.id = id;
        this.member = member;
        this.book = book;
        this.started = started;
        this.finished = finished;
    }

    public LendModel(String member, String book, long started, long finished) {
        this.member = member;
        this.book = book;
        this.started = started;
        this.finished = finished;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public long getStarted() {
        return started;
    }

    public void setStarted(long started) {
        this.started = started;
    }

    public long getFinished() {
        return finished;
    }

    public void setFinished(long finished) {
        this.finished = finished;
    }
}
