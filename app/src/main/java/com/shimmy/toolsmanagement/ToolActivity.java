package com.shimmy.toolsmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ToolActivity extends AppCompatActivity {

    public static final String TOOL_ID_KEY = "toolId";

    private TextView txtToolName, txtManufacturer;
    private Button btnAddToCurrentlyTaken, btnAddToWishlist, btnAddToFavorites;
    private ImageView toolimgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool);

        initView();

        //Retrieving the data from ToolRepository
        Intent intent = getIntent();
        //watch out for empty intent - for safety I'm going to continue in if{}
        if (intent != null){
            int toolId = intent.getIntExtra(TOOL_ID_KEY, -1);
            if (toolId != -1){
                Tool incomingTool = ToolsRepository.getInstance().getToolById(toolId);
                if (incomingTool != null){
                    setData(incomingTool);

                    handleCurrentlyTakenTools(incomingTool);
                    handleWantToHaveTools(incomingTool);
                    handleFavoritesTools(incomingTool);
                    handleAlreadyUsedTools(incomingTool);
                }
            }
        }
    }

    private void handleAlreadyUsedTools(final Tool incomingTool) {
        List<Tool> alreadyUsedTools = ToolsRepository.getInstance().getAlreadyUsedTools();

        boolean existInAlreadyUsedTools = false;

        for (Tool t: alreadyUsedTools){
            if (t.getId() == incomingTool.getId()){
                existInAlreadyUsedTools = true;
            }
        }
        if (existInAlreadyUsedTools){
            //TODO: logic for handling the History of Usage - AlreadyUsedTools form CurrentlyTaken
            //there is no button now: btnAddToCurrentlyTaken.setEnabled(false);
        }else {

        }
    }

    private void handleFavoritesTools(final Tool incomingTool) {
        List<Tool> favortitesTools = ToolsRepository.getInstance().getFavoritesTools();

        boolean existInFavoritesTools = false;

        for (Tool t: favortitesTools){
            if (t.getId() == incomingTool.getId()){
                existInFavoritesTools = true;
            }
        }
        if (existInFavoritesTools){
            btnAddToFavorites.setEnabled(false);
        }else {
            btnAddToFavorites.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (ToolsRepository.getInstance().addToFavorites(incomingTool)){
                        Toast.makeText(ToolActivity.this, "Tool added", Toast.LENGTH_SHORT).show();
                        //navigate the user to WishlistActivity by Intent
                        Intent intent = new Intent(ToolActivity.this, FavoriteActivity.class);
                        startActivity(intent);

                    }else{
                        Toast.makeText(ToolActivity.this, "Something wrong happened, try again", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    }

    private void handleWantToHaveTools(final Tool incomingTool) {
        List<Tool> wantToHaveTools = ToolsRepository.getInstance().getWantToHaveTools();

        boolean existInWishlist = false;

        for (Tool t: wantToHaveTools){
            if (t.getId() == incomingTool.getId()){
                existInWishlist = true;
            }
        }
        if (existInWishlist){
            btnAddToWishlist.setEnabled(false);
        }else {
            btnAddToWishlist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (ToolsRepository.getInstance().addToWantToHave(incomingTool)){
                        Toast.makeText(ToolActivity.this, "Tool added", Toast.LENGTH_SHORT).show();
                        //navigate the user to FavoriteActivity by Intent
                        Intent intent = new Intent(ToolActivity.this, WishlistActivity.class);
                        startActivity(intent);

                    }else{
                        Toast.makeText(ToolActivity.this, "Something wrong happened, try again", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    }

    /**
     * Enable and disable button
     * Add the tool to AlreadyTakenTools List
     * @param incomingTool
     */
    private void handleCurrentlyTakenTools(final Tool incomingTool) {
        List<Tool> currentlyTakenTools = ToolsRepository.getInstance().getCurrentlyTakenTools();

        boolean existInCurrentlyTaken = false;

        for (Tool t: currentlyTakenTools){
            if (t.getId() == incomingTool.getId()){
                existInCurrentlyTaken = true;
            }
        }
        if (existInCurrentlyTaken){
            btnAddToCurrentlyTaken.setEnabled(false);
        }else {
            btnAddToCurrentlyTaken.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (ToolsRepository.getInstance().addToCurrentlyTakenTools(incomingTool)){
                        Toast.makeText(ToolActivity.this, "Tool added", Toast.LENGTH_SHORT).show();
                        //navigate the user to CurrentlyTakenToolsActivity by Intent
                        Intent intent = new Intent(ToolActivity.this, CurrentlyTakenToolsActivity.class);
                        startActivity(intent);

                    }else{
                        Toast.makeText(ToolActivity.this, "Something wrong happened, try again", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
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