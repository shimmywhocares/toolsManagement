package com.shimmy.toolsmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnAllTools, btnCurrentlyTaken, btnWantToHave, btnFavorite, btnUsedTools, btnAbout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        btnAllTools.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AllToolsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        btnAllTools = findViewById(R.id.btnAllTools);
        btnCurrentlyTaken = findViewById(R.id.btnCurrentlyTaken);
        btnFavorite = findViewById(R.id.btnFavorite);
        btnWantToHave = findViewById(R.id.btnWantToHave);
        btnUsedTools = findViewById(R.id.btnUsedTools);
        btnAbout = findViewById(R.id.btnAbout);


    }
}