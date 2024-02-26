package com.example.acoxtseller.Model;

import android.widget.TextView;

public class Model_order_list_detail {
    String item_name;

    public Model_order_list_detail(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }
}
