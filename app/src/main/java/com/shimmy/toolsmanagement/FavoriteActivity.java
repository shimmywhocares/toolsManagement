package com.shimmy.toolsmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

public class FavoriteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        RecyclerView favoritesRecView = findViewById(R.id.favoritesRecView);
        ToolRecViewAdapter adapter = new ToolRecViewAdapter(this);
        favoritesRecView.setAdapter(adapter);
        favoritesRecView.setLayoutManager(new LinearLayoutManager(this));

        adapter.setToolsArrayList(ToolsRepository.getInstance().getFavoritesTools());
    }

    //Handle the Back Button action from FavoritesActivity
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    }
