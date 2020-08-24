package com.shimmy.toolsmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

public class CurrentlyTakenToolsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currently_taken_tools);

        RecyclerView currentlyTakenRecView = findViewById(R.id.currentlyTakenToolsRecView);
        ToolRecViewAdapter adapter = new ToolRecViewAdapter(this);
        currentlyTakenRecView.setAdapter(adapter);
        currentlyTakenRecView.setLayoutManager(new LinearLayoutManager(this));

        adapter.setToolsArrayList(ToolsRepository.getInstance().getCurrentlyTakenTools());
    }

    //Handle the Back Button action from CurrentlyTakenToolsActivity
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}