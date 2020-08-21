package com.shimmy.toolsmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ToolActivity extends AppCompatActivity {

    private TextView txtToolName, txtManufacturer;
    private Button btnAddToCurrentlyTaken, btnAddToWishlist, btnAddToFavorites;
    private ImageView toolimgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool);

        initView();

        //TODO: Get incoming data from recycler view in here
        Tool tool = new Tool(1000, "Wierto", "Sandvik", "Szafa nr 2", "W magazynie", "magazyn", 15, "https://images-na.ssl-images-amazon.com/images/I/71gHlVx1SRL._SL1500_.jpg", "wiertło sandvik super-extra-mega-wypas-do_metalu i do drewna");

        setData(tool);

    }

    private void setData(Tool tool){
        txtToolName.setText(tool.getName());
        txtManufacturer.setText(tool.getManufacturer());
        Glide.with(this)
                .asBitmap().load(tool.getImageUrl())
                .into(toolimgView);
    }

    private void initView() {
        txtToolName = findViewById(R.id.txtToolName);
        txtManufacturer = findViewById(R.id.txtManufacturer);

        btnAddToCurrentlyTaken = findViewById(R.id.btnAddToCurrentlyTaken);
        btnAddToFavorites = findViewById(R.id.btnAddToFavorites);
        btnAddToWishlist = findViewById(R.id.btnAddToWishlist);

        toolimgView = findViewById(R.id.toolimageView);
    }
}