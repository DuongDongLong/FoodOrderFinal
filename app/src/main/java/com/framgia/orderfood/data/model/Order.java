package com.framgia.orderfood.data.model;

import java.util.ArrayList;
import java.util.List;


public class Order {
    private List<Cart> cart;
    private String table;
    private double total;
    private String status;

    public Order( List<Cart> cart,String table, double total, String status) {
        this.cart = cart;
        this.table = table;
        this.total = total;
        this.status = status;
    }

    public List<Cart> getCart() {
        return cart;
    }

    public void setCart(List<Cart> cart) {
        this.cart = cart;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
