package com.shimmy.toolsmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class AddToolActivity extends AppCompatActivity {

    private static final String TAG = "AddToolActivity";

    private EditText editTxtToolName, editTxtManufacturer, editTxtDimensions, editTxtShortDesc;
    private Button btnAddTool;
    private ImageView imgTool;

    private ConstraintLayout parentConstLayoutAddToolActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tool);

        initView();
    }

    private void initView() {
        Log.d(TAG, "initView: AddTool started");
        editTxtToolName = findViewById(R.id.editTxtToolName);
        editTxtManufacturer = findViewById(R.id.editTxtManufacturer);
        editTxtDimensions = findViewById(R.id.editTxtDimensions);
        editTxtShortDesc = findViewById(R.id.editTxtShortDesc);

        btnAddTool = findViewById(R.id.btnAddTool);

        imgTool = findViewById(R.id.imgTool);

        parentConstLayoutAddToolActivity = findViewById(R.id.parentConstLayoutAddToolActivity);

    }
}