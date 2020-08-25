package com.shimmy.toolsmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class BarcodeScannerActivity extends AppCompatActivity {

    private static final String TAG = "BarcodeScannerActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_scanner);
        ToolsRepository.getInstance();
        scanCode();


    }

    public void scanCode() {
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        //now I have the scanResult of scanning!
        if(scanResult != null) {
            if(scanResult.getContents() != null) {
                //passing the barcode by List
//                listOfBarcodes.add(scanResult.getContents());

                //when the adding of barcodes is done: call the ListViewActivity and pass the listOfBarcodes as a Serializable.
//                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
//                intent.putExtra("BARCODE_CAPTURE", listOfBarcodes);
//                startActivity(intent);

                //passing the barcode by Intent
                //ver z ToolRepository?!
//                Intent dataPassing = new Intent(getApplicationContext(), ToolsRepository.class);
//                dataPassing.putExtra( "BARCODE_CAPTURE", scanResult.getContents() );
//                startActivity(dataPassing);

                //passing by Shimmy
                ToolsRepository.getInstance().setIncomingBarcodeId((scanResult.getContents()));
                Toast.makeText(this, "Scanned: " + scanResult.getContents(), Toast.LENGTH_LONG).show();
                Log.d(TAG, "onActivityResult: Id passed or not?");

                Intent goBack = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(goBack);


//                AlertDialog.Builder builder = new AlertDialog.Builder(this);
//                builder.setMessage(scanResult.getContents());
//                builder.setTitle("Scanning Result");
//                builder.setPositiveButton("Scan Again", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int which) {
//                        scanCode();
//                    }
//                }).setNegativeButton("Finish", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int which) {
//                        //no code here means that after scanning the UI stay in MainActivity
////                        Intent intent = new Intent(MainActivity.this, MainActivity.class);
////                        startActivity(intent);
//                    }
//                });
//                AlertDialog dialog = builder.create();
//                dialog.show();

            } else {
                Toast.makeText(this, "No Results", Toast.LENGTH_LONG).show();
                //Toast.makeText(this, "Scanned: " + scanResult.getContents(), Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}