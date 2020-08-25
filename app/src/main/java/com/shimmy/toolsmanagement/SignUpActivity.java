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
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class SignUpActivity extends AppCompatActivity {
    private static final String TAG = "SignUpActivity";

    private TextView txtTest;
    private EditText editTxtPersonName, editTxtEmail, editTxtPassword, editTxtPassRepeat;
    private Button btnSignUp;
    //private TextView txtWarnName, txtWarnEmail, txtWarnPass, txtWarnPassRepeat;
    private RadioGroup rbGender;
    private CheckBox agreementCheck;
    private ConstraintLayout parentConstLaySignUpActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        
        initView();
    }

    private void initView() {
        Log.d(TAG, "initView: Started");
        editTxtPersonName = findViewById(R.id.editTxtPersonName);
        editTxtEmail = findViewById(R.id.editTxtEmail);
        editTxtPassword = findViewById(R.id.editTxtDimensions);
        editTxtPassRepeat = findViewById(R.id.editTxtPassRepeat);

//        txtWarnName = findViewById(R.id.txtWarnName);
//        txtWarnEmail = findViewById(R.id.txtWarnEmail);
//        txtWarnPass = findViewById(R.id.txtWarnPass);
//        txtWarnPassRepeat = findViewById(R.id.txtWarnPassRepeat);

        rbGender = findViewById(R.id.rbGroup);
        agreementCheck = findViewById(R.id.agreementCheck);
        btnSignUp = findViewById(R.id.btnAddTool);

        parentConstLaySignUpActivity = findViewById(R.id.parentConstLaySignUpActivity);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initSignUp();
            }
        });


//        Intent callingIntent = getIntent();
//        String receivedBarcode = callingIntent.getStringExtra("BARCODE_CAPTURE");
        Log.d(TAG, "initView: Did I get the package or not");
        txtTest = findViewById(R.id.txtTest);
        txtTest.setText(ToolsRepository.getInstance().getIncomingBarcodeId());



    }

    private void initSignUp() {
        Log.d(TAG, "initSignUp: Started");
        
        if (validateData()) {
            if (agreementCheck.isChecked()){
                showSnackBarSignedUp();
                //TODO: ParseUser;
            }else {
                Toast.makeText(this, "You need to agree to the licence agreement", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void showSnackBarSignedUp() {
        Log.d(TAG, "showSnackBarSignedUp: Started");

        Snackbar.make(parentConstLaySignUpActivity, "User Registered", Snackbar.LENGTH_INDEFINITE)
                .setAction("Verify your e-mail", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //TODO: Intent go to the SignUpActivity
                    }
                }).show();
    }

    private boolean validateData() {
        Log.d(TAG, "validateData: Started");
        if(editTxtPersonName.getText().toString().equals("")){
            editTxtPersonName.setError("Enter your Name");
            return false;
        }
        if(editTxtEmail.getText().toString().equals("")){
            editTxtEmail.setError("Enter your E-mail");

            return false;
        }
        if(editTxtPassword.getText().toString().equals("")){
            editTxtPassword.setError("Enter your Password");

            return false;
        }
        if(editTxtPassRepeat.getText().toString().equals("")){
            editTxtPassRepeat.setError("Enter your Password again");

            return false;
        }
        return true;
    }
}