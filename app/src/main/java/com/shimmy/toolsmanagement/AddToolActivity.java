package com.shimmy.toolsmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class AddToolActivity extends AppCompatActivity {

    private static final String TAG = "AddToolActivity";

    private EditText editTxtToolName, editTxtManufacturer, editTxtDimensions, editTxtShortDesc;
    private Button btnAddTool;
    private CheckBox checkBoxData;
    private ImageView imgTool;

    private ConstraintLayout parentConstLayoutAddToolActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tool);

        initView();
        btnAddTool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initAddingTool();
            }
        });

    }
    private void initAddingTool() {
        Log.d(TAG, "initAddingTool: started");

        if (validateData()) {
            if (checkBoxData.isChecked()){
                passingValuesToRepository();
                showSnackBarToolAdded();
            }else {
                Toast.makeText(this, "You need to confirm the entered data", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void showSnackBarToolAdded() {
        Log.d(TAG, "showSnackBarAddTool: Started");

        Snackbar.make(parentConstLayoutAddToolActivity, "Tool added", Snackbar.LENGTH_INDEFINITE)
                .setAction("Go back to All Tools", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(), AllToolsActivity.class);
                        startActivity(intent);
                    }
                }).show();
    }
    private boolean validateData() {
        Log.d(TAG, "validateData: started");
        if(editTxtToolName.getText().toString().equals("")){
            editTxtToolName.setError("Enter the name of tool");
            return false;
        }
        if(editTxtManufacturer.getText().toString().equals("")){
            editTxtManufacturer.setError("Enter the manufacturer of tool");

            return false;
        }
        if(editTxtDimensions.getText().toString().equals("")){
            editTxtDimensions.setError("Enter important dimensions");

            return false;
        }
        if(editTxtShortDesc.getText().toString().equals("")){
            editTxtShortDesc.setError("Enter a short description");

            return false;
        }
        return true;
    }
    private void passingValuesToRepository(){
        ToolsRepository.getInstance().addTool(
                editTxtToolName.getText().toString(),
                editTxtManufacturer.getText().toString(),
                editTxtDimensions.getText().toString(),
                editTxtShortDesc.getText().toString());
    }
    private void initView() {
        Log.d(TAG, "initView: AddTool started");
        editTxtToolName = findViewById(R.id.editTxtToolName);
        editTxtManufacturer = findViewById(R.id.editTxtManufacturer);
        editTxtDimensions = findViewById(R.id.editTxtDimensions);
        editTxtShortDesc = findViewById(R.id.editTxtShortDesc);

        btnAddTool = findViewById(R.id.btnAddTool);
        checkBoxData = findViewById(R.id.checkBoxData);

        imgTool = findViewById(R.id.imgTool);

        parentConstLayoutAddToolActivity = findViewById(R.id.parentConstLayoutAddToolActivity);

    }
}