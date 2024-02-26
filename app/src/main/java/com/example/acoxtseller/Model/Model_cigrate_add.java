package com.example.acoxtseller.Model;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Model_cigrate_add {
    String cigrate_count;

    public Model_cigrate_add(String cigrate_count) {
        this.cigrate_count = cigrate_count;
    }

    public String getCigrate_count() {
        return cigrate_count;
    }

    public void setCigrate_count(String cigrate_count) {
        this.cigrate_count = cigrate_count;
    }
}
