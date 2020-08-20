package com.shimmy.toolsmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.shimmy.toolsmanagement.R;

import java.util.ArrayList;

public class AllToolsActivity extends AppCompatActivity {

    private RecyclerView toolsRecView;
    private ToolRecViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_tools_acivity);

        adapter = new ToolRecViewAdapter(this);
        toolsRecView = findViewById(R.id.toolsRecView);

        toolsRecView.setAdapter(adapter);
        toolsRecView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Tool> tools = new ArrayList<>();
        tools.add(new Tool(1000, "Wierto", "Sandvik", "Szafa nr 2", "W magazynie", "magazyn", 15, "https://images-na.ssl-images-amazon.com/images/I/71gHlVx1SRL._SL1500_.jpg", "wiertło sandvik super-extra-mega-wypas-do_metalu i do drewna"));
        tools.add(new Tool(2000, "Piła", "Husqvarna", "Szafa nr 5", "W lesie", "Staszek", 34, "https://www.forestandarb.com/res/Husqvarna%20120%20Mk%20II.jpg", "Piła łieeee łieeee!"));
        tools.add(new Tool(2000, "Młotek", "STANLEY", "Szafa nr 0", "Przy kowadle", "Zbyszek", +100, "https://www.stanleytools.com/~/media/stanleytools/images/listing-images/antivibe_hammers.jpg", "Młotek stuk stuk buch buch!"));
        adapter.setToolsArrayList(tools);
    }
}