package com.shimmy.toolsmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

public class AlreadyUsedToolsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_already_used);

        RecyclerView UsedToolsRecView = findViewById(R.id.alreadyUsedToolsRecView);
        ToolRecViewAdapter adapter = new ToolRecViewAdapter(this);
        UsedToolsRecView.setAdapter(adapter);
        UsedToolsRecView.setLayoutManager(new LinearLayoutManager(this));

        adapter.setToolsArrayList(ToolsRepository.getInstance().getAlreadyUsedTools());
    }

    //Handle the Back Button action from FavoritesActivity
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
