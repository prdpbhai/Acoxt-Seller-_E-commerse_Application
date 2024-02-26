package com.example.acoxtseller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acoxtseller.Adapter.Adapter_Inventary;
import com.example.acoxtseller.Model.Model_Inventary;
import com.example.acoxtseller.Model.Model_bank_list;

import java.util.ArrayList;
import java.util.List;

public class Inventory_Product_Name_Activity extends AppCompatActivity {
    TextView category_popup;
    RecyclerView inventary_recyclerview;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_product_name);
        category_popup=findViewById(R.id.category_popup_down);
        category_popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(Inventory_Product_Name_Activity.this, category_popup);

                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        Toast.makeText(Inventory_Product_Name_Activity.this, "You Clicked " + menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
        inventary_recyclerview=findViewById(R.id.inventary_recyclerview);
        List<Model_Inventary>model_inventaries=new ArrayList<>();
        model_inventaries.add(new Model_Inventary("Stellar Define","Paan corner","100"));
        model_inventaries.add(new Model_Inventary("Stellar Define","Paan corner","100"));
        model_inventaries.add(new Model_Inventary("Stellar Define","Paan corner","100"));
        model_inventaries.add(new Model_Inventary("Stellar Define","Paan corner","100"));
        model_inventaries.add(new Model_Inventary("Stellar Define","Paan corner","100"));
        model_inventaries.add(new Model_Inventary("Stellar Define","Paan corner","100"));
        model_inventaries.add(new Model_Inventary("Stellar Define","Paan corner","100"));
        model_inventaries.add(new Model_Inventary("Stellar Define","Paan corner","100"));
        model_inventaries.add(new Model_Inventary("Stellar Define","Paan corner","100"));
        model_inventaries.add(new Model_Inventary("Stellar Define","Paan corner","100"));
        model_inventaries.add(new Model_Inventary("Stellar Define","Paan corner","100"));
        model_inventaries.add(new Model_Inventary("Stellar Define","Paan corner","100"));

        inventary_recyclerview.setLayoutManager(new LinearLayoutManager(this));
//        inventary_recyclerview.setAdapter(new Adapter_Inventary(getApplicationContext(),model_inventaries));
//        return null;
    }
}