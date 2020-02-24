package com.example.shopping;

public class Data {

    String   name, description, price;
    String mImageUrl;

    public Data(String name, String mImageUrl, String price, String description) {

    }

    public Data(String category, String name, String description, String price, String image) {

        this.name = name;
        this.description = description;
        this.price = price;
        this.mImageUrl = image;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getmImageUrl() {
        return mImageUrl;}


    public void setImage(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }
}
