package com.example.acoxtseller.Model;

public class Model_Inventary {
    String product_name,category_name,stock_left;

    public Model_Inventary(String product_name, String category_name, String stock_left) {
        this.product_name = product_name;
        this.category_name = category_name;
        this.stock_left = stock_left;
    }


    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getStock_left() {
        return stock_left;
    }

    public void setStock_left(String stock_left) {
        this.stock_left = stock_left;
    }
}
