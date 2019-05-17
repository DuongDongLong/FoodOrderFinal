package com.framgia.orderfood.data.model;

public class Cart {
    private String Name;
    private String Description;
    private String Image;
    private String Discount;
    private String MenuId;
    private String Price;
    private int Quantity;

    public Cart(String name, String description, String image, String discount, String menuId, String price, int quantity) {
        Name = name;
        Description = description;
        Image = image;
        Discount = discount;
        MenuId = menuId;
        Price = price;
        Quantity = quantity;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getDiscount() {
        return Discount;
    }

    public void setDiscount(String discount) {
        Discount = discount;
    }

    public String getMenuId() {
        return MenuId;
    }

    public void setMenuId(String menuId) {
        MenuId = menuId;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }
}
