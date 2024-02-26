package com.example.acoxtseller.Model;

public class Model_Calender {
    String month,date;

    public Model_Calender(String month, String date) {
        this.month = month;
        this.date = date;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
