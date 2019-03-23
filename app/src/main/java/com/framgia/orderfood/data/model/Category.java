package com.framgia.orderfood.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Category implements Parcelable {
    private String Name;
    private String Image;

    public Category( String mFoodName, String mImageUrl) {
        this.Name = mFoodName;
        this.Image = mImageUrl;
           }


    public Category(CategoryBuilder builder) {
        Name = builder.mCategoryName;
        Image = builder.mImageUrl;

    }

    protected Category(Parcel in) {
        Name = in.readString();
        Image = in.readString();
    }

    public String getCategoryName() {
        return Name;
    }

    public void setCategoryName(String mCategoryName) {
        this.Name = mCategoryName;
    }

    public String getImageUrl() {
        return Image;
    }

    public void setImageUrl(String mImageUrl) {
        this.Image = mImageUrl;
    }

    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Name);
        dest.writeString(Image);
    }

    public static class CategoryBuilder {
        private String mCategoryName;
        private String mImageUrl;

        public CategoryBuilder setFoodName(String name) {
            mCategoryName = name;
            return this;
        }

        public CategoryBuilder setImageUrl(String url) {
            mImageUrl = url;
            return this;
        }

        public Category build() {
            return new Category(this);
        }
    }

    public final class CategoryEntry {
        public static final String ID = "";
        public static final String NAME = "";
        public static final String IMAGE_URL = "";
    }
}
