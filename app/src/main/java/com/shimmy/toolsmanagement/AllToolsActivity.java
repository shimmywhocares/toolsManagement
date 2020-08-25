package com.shimmy.toolsmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

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

        //plug-in the singleton repository
        ToolsRepository toolsRepository = ToolsRepository.getInstance();
        adapter.setToolsArrayList(toolsRepository.getToolsAll());
    }
}