package com.example.activity1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.List;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.activity1.R;

public class MainActivity7 extends AppCompatActivity {
    private Spinner spnMenu;
    private RecyclerView recyclerView;
    private CustomListAdapter adapter;
    private List<Menultem> itemsList;
    private DatabaseItems databaseItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        spnMenu = findViewById(R.id.spinner);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        databaseItems = new DatabaseItems();
        itemsList = databaseItems.getAllMenuItems();

        adapter = new CustomListAdapter(this, itemsList);
        recyclerView.setAdapter(adapter);

        setupSpinner();
    }

    private void setupSpinner() {
        String[] categories = databaseItems.getCategories();
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, categories);
        spnMenu.setAdapter(spinnerAdapter);

        spnMenu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCategory = categories[position];
                itemsList.clear();
                if (selectedCategory.equals("All")) {
                    itemsList.addAll(databaseItems.getAllMenuItems());
                } else {
                    itemsList.addAll(databaseItems.getMenuItems(selectedCategory));
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
    }
}