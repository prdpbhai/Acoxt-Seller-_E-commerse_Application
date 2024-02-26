package com.example.acoxtseller.Model;

import android.widget.LinearLayout;
import android.widget.TextView;

public class Model_order_manage {
    String order_id;

    public Model_order_manage(String order_id) {
        this.order_id = order_id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }
}
