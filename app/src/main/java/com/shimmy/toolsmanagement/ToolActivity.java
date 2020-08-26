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

import java.util.List;

public class ToolActivity extends AppCompatActivity {

    public static final String TOOL_ID_KEY = "toolId";

    private TextView txtToolName, txtManufacturer, txtDimensions, txtStatus, txtHolder, txtHoursOfUsage, txtToolId, txtDesc;
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
                    setDataToDisplay(incomingTool);

                    handleCurrentlyTakenTools(incomingTool);
                    handleWantToHaveTools(incomingTool);
                    handleFavoritesTools(incomingTool);

                }
            }
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


    private void setDataToDisplay(Tool tool){
        txtToolName.setText(tool.getName());
        txtManufacturer.setText(tool.getManufacturer());
        txtDimensions.setText(tool.getDimensions());
        txtStatus.setText(tool.getStatus());
        txtHolder.setText(tool.getHolder());
        txtHoursOfUsage.setText(String.valueOf(tool.getHoursOfUsage()));
        txtToolId.setText(String.valueOf(tool.getId()));
        txtDesc.setText(tool.getShortDesc());
        Glide.with(this)
                .asBitmap().load(tool.getImageUrl())
                .into(toolimgView);
    }
    private void initView() {
        txtToolName = findViewById(R.id.txtSetToolName);
        txtManufacturer = findViewById(R.id.txtSetManufacturer);
        txtDimensions = findViewById(R.id.txtSetDimensions);
        txtStatus = findViewById(R.id.txtSetStatus);
        txtHolder = findViewById(R.id.txtSetHolder);
        txtHoursOfUsage = findViewById(R.id.txtSetHoursOfUsage);
        txtToolId = findViewById(R.id.txtSetToolId);
        txtDesc = findViewById(R.id.txtSetDescription);

        btnAddToCurrentlyTaken = findViewById(R.id.btnAddToCurrentlyTaken);
        btnAddToFavorites = findViewById(R.id.btnAddToFavorites);
        btnAddToWishlist = findViewById(R.id.btnAddToWishlist);

        toolimgView = findViewById(R.id.toolimageView);
    }
}