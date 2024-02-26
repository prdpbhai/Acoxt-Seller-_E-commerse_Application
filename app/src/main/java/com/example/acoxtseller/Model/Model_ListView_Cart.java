package com.example.acoxtseller.Model;

public class Model_ListView_Cart {
    String item_name;

    public Model_ListView_Cart(String item_name) {
        this.item_name = String.valueOf(item_name);
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

}
