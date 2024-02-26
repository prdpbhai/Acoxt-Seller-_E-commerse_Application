package com.example.acoxtseller.Model;

public class Model_cigrate_list {
    String item_name_cig;
    int item_type;

    public Model_cigrate_list(String item_name_cig, int item_type) {
        this.item_name_cig = item_name_cig;
        this.item_type = item_type;
    }

    public String getItem_name_cig() {
        return item_name_cig;
    }

    public void setItem_name_cig(String item_name_cig) {
        this.item_name_cig = item_name_cig;
    }

    public int getItem_type() {
        return item_type;
    }

    public void setItem_type(int item_type) {
        this.item_type = item_type;
    }
}
