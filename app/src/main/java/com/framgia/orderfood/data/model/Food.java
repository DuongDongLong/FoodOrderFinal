package com.framgia.orderfood.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Food implements Parcelable {
    String Name;
    String Description;
    String Image;
    String Discount;
    String MenuId;
    String Price;

    protected Food(Parcel in) {
        Name = in.readString();
        Description = in.readString();
        Image = in.readString();
        Discount = in.readString();
        MenuId = in.readString();
        Price = in.readString();
    }

    public static final Creator<Food> CREATOR = new Creator<Food>() {
        @Override
        public Food createFromParcel(Parcel in) {
            return new Food(in);
        }

        @Override
        public Food[] newArray(int size) {
            return new Food[size];
        }
    };

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

    public Food(String name, String description, String image, String discount, String menuId, String price) {
        Name = name;
        Description = description;
        Image = image;
        Discount = discount;
        MenuId = menuId;
        Price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Name);
        dest.writeString(Description);
        dest.writeString(Image);
        dest.writeString(Discount);
        dest.writeString(MenuId);
        dest.writeString(Price);
    }
}
