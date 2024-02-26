package com.example.acoxtseller.Model;

public class Model_checkout_recyclerview {
    String item_weight,item_rate,item_name;
    int item_image;

    public Model_checkout_recyclerview(String item_weight, String item_rate, String item_name, int item_image) {
        this.item_weight = item_weight;
        this.item_rate = item_rate;
        this.item_name = item_name;
        this.item_image = item_image;
    }

    public String getItem_weight() {
        return item_weight;
    }

    public void setItem_weight(String item_weight) {
        this.item_weight = item_weight;
    }

    public String getItem_rate() {
        return item_rate;
    }

    public void setItem_rate(String item_rate) {
        this.item_rate = item_rate;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public int getItem_image() {
        return item_image;
    }

    public void setItem_image(int item_image) {
        this.item_image = item_image;
    }
}
