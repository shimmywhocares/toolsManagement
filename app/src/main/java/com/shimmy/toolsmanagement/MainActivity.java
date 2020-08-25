package com.shimmy.toolsmanagement;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Button btnAllTools, btnCurrentlyTaken, btnWantToHave, btnFavorite, btnUsedTools,
    btnAbout;
    private FloatingActionButton fab;

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

        btnCurrentlyTaken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CurrentlyTakenToolsActivity.class);
                startActivity(intent);
            }
        });

        btnWantToHave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WishlistActivity.class);
                startActivity(intent);
            }
        });

        btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FavoriteActivity.class);
                startActivity(intent);
            }
        });

        btnUsedTools.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intnt = new Intent(MainActivity.this, AlreadyUsedToolsActivity.class);
                startActivity(intnt);
            }
        });

        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddToolActivity.class);
                startActivity(intent);
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // barcode scanner
//                BarcodeScanner scanner = new BarcodeScanner();
//                scanner.scanCode();
                Intent intent = new Intent(MainActivity.this, BarcodeScannerActivity.class);
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

        //Floating action button
        fab = findViewById(R.id.fab);
    }

    //the barcode scanner code
    private void scanCode() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        //next line yields the portrait view of scanner
        integrator.setCaptureActivity(CaptureAct.class);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Scan a barcode");
        integrator.setCameraId(0);  // Use a specific camera of the device
        integrator.setBeepEnabled(false);
        integrator.setBarcodeImageEnabled(true);
        integrator.initiateScan();
    }


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//    IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
//    //now I have the scanResult of scanning!
//    if(scanResult != null) {
//        if(scanResult.getContents() != null) {
//            //passing the barcode by List
////                listOfBarcodes.add(scanResult.getContents());
//
//            //when the adding of barcodes is done: call the ListViewActivity and pass the listOfBarcodes as a Serializable.
////                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
////                intent.putExtra("BARCODE_CAPTURE", listOfBarcodes);
////                startActivity(intent);
//
//            //passing the barcode by Intent
//            Intent dataPassing = new Intent( getApplicationContext(), SignUpActivity.class );
//            dataPassing.putExtra( "BARCODE_CAPTURE", scanResult.getContents() );
//            startActivity(dataPassing);
//
////                AlertDialog.Builder builder = new AlertDialog.Builder(this);
////                builder.setMessage(scanResult.getContents());
////                builder.setTitle("Scanning Result");
////                builder.setPositiveButton("Scan Again", new DialogInterface.OnClickListener() {
////                    @Override
////                    public void onClick(DialogInterface dialogInterface, int which) {
////                        scanCode();
////                    }
////                }).setNegativeButton("Finish", new DialogInterface.OnClickListener() {
////                    @Override
////                    public void onClick(DialogInterface dialogInterface, int which) {
////                        //no code here means that after scanning the UI stay in MainActivity
//////                        Intent intent = new Intent(MainActivity.this, MainActivity.class);
//////                        startActivity(intent);
////                    }
////                });
////                AlertDialog dialog = builder.create();
////                dialog.show();
//
//        } else {
//            Toast.makeText(this, "No Results", Toast.LENGTH_LONG).show();
//            //Toast.makeText(this, "Scanned: " + scanResult.getContents(), Toast.LENGTH_LONG).show();
//        }
//    } else {
//        super.onActivityResult(requestCode, resultCode, data);
//    }
//}
}