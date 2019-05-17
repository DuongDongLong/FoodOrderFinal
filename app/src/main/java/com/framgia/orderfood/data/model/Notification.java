package com.framgia.orderfood.data.model;

import java.util.List;

public class Notification {
    private List<Cart> carts;
    private String table,title,note,time,status;

    public Notification(List<Cart> carts, String table, String title, String note, String time, String status) {
        this.carts = carts;
        this.table = table;
        this.title = title;
        this.note = note;
        this.time = time;
        this.status = status;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
