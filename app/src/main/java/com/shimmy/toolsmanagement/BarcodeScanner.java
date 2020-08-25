package com.shimmy.toolsmanagement;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class BarcodeScanner extends AppCompatActivity {

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
                ToolsRepository.getInstance().setIncomingBarcodeId((scanResult.getContents()));

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
