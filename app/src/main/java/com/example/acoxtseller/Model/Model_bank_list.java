package com.example.acoxtseller.Model;

import android.widget.ImageView;

public class Model_bank_list {
    int bank_icon;
    String bank_name;

    public Model_bank_list(int bank_icon, String bank_name) {
        this.bank_icon = bank_icon;
        this.bank_name = bank_name;
    }

    public int getBank_icon() {
        return bank_icon;
    }

    public void setBank_icon(int bank_icon) {
        this.bank_icon = bank_icon;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }
}
