package com.shimmy.toolsmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CurrentlyTakenToolsActivity extends AppCompatActivity {

    private FloatingActionButton fabCurrentluTaken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currently_taken_tools);

        fabCurrentluTaken = findViewById(R.id.fabCurrentlyTaken);
        fabCurrentluTaken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToolsRepository.getInstance().removeFromCurrentlyTaken("2000");
                //TODO: boolean
                Toast.makeText(CurrentlyTakenToolsActivity.this, "Tool removed ", Toast.LENGTH_SHORT).show();
            }
        });

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