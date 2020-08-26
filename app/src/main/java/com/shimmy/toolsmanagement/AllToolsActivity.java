package com.shimmy.toolsmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AllToolsActivity extends AppCompatActivity {

    private RecyclerView toolsRecView;
    private ToolRecViewAdapter adapter;
    private FloatingActionButton fabAddTool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_tools_acivity);

        initView();

        fabAddTool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddToolActivity.class);
                startActivity(intent);
            }
        });

    }

    void initView(){
        fabAddTool = findViewById(R.id.fabAddTool);

        adapter = new ToolRecViewAdapter(this);
        toolsRecView = findViewById(R.id.toolsRecView);
        toolsRecView.setAdapter(adapter);
        toolsRecView.setLayoutManager(new LinearLayoutManager(this));
        //plug-in the singleton repository
        ToolsRepository toolsRepository = ToolsRepository.getInstance();
        adapter.setToolsArrayList(toolsRepository.getToolsAll());
    }
}