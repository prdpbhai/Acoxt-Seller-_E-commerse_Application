package com.example.acoxtseller.Model;

import android.widget.TextView;

public class Model_add_productList {
    String item_Name;

    public Model_add_productList(String item_Name) {
        this.item_Name = item_Name;
    }

    public String getItem_Name() {
        return item_Name;
    }

    public void setItem_Name(String item_Name) {
        this.item_Name = item_Name;
    }
}
