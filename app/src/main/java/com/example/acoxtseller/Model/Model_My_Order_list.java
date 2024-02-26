package com.example.acoxtseller.Model;

import android.widget.ImageView;

public class Model_My_Order_list {
    String date,time,order_id,rate;
    int item_icon;

    public Model_My_Order_list(String date, String time, String order_id, String rate, int item_icon) {
        this.date = date;
        this.time = time;
        this.order_id = order_id;
        this.rate = rate;
        this.item_icon = item_icon;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public int getItem_icon() {
        return item_icon;
    }

    public void setItem_icon(int item_icon) {
        this.item_icon = item_icon;
    }
}
