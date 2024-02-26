package com.example.acoxtseller.Model;

import android.widget.ImageView;

public class Model_cigrate_Name {
    String cig_name;
    int product_image_munchies;

    public Model_cigrate_Name(String cig_name, int product_image_munchies) {
        this.cig_name = cig_name;
        this.product_image_munchies = product_image_munchies;
    }

    public String getCig_name() {
        return cig_name;
    }

    public void setCig_name(String cig_name) {
        this.cig_name = cig_name;
    }

    public int getProduct_image_munchies() {
        return product_image_munchies;
    }

    public void setProduct_image_munchies(int product_image_munchies) {
        this.product_image_munchies = product_image_munchies;
    }
}
