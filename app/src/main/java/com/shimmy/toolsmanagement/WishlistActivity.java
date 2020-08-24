package com.shimmy.toolsmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

public class WishlistActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);

        RecyclerView wishlistRecView = findViewById(R.id.wishlistRecView);
        ToolRecViewAdapter adapter = new ToolRecViewAdapter(this);
        wishlistRecView.setAdapter(adapter);
        wishlistRecView.setLayoutManager(new LinearLayoutManager(this));

        adapter.setToolsArrayList(ToolsRepository.getInstance().getWantToHaveTools());
    }

    //Handle the Back Button action from WishlistActivity
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}