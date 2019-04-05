package com.framgia.orderfood.data.model;

public class Cart {
    private Food food;
    private int Quatity;

    public Cart(Food food, int quatity) {
        this.food = food;
        Quatity = quatity;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public int getQuatity() {
        return Quatity;
    }

    public void setQuatity(int quatity) {
        Quatity = quatity;
    }
}
