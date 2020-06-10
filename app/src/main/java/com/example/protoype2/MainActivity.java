package com.example.protoype2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    Button scanBtn, dummy, addDummy, historyBtn;
    EditText workerID, rssiValue;
    public BluetoothAdapter BA = BluetoothAdapter.getDefaultAdapter();
    private static final String TAG = "MainActivity";
    public static final int REQUEST_ENABLE_BT = 1;
    private String ID;
    private int rssi;
    DataHandler dh = new DataHandler();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            Utils.toast(getApplicationContext(), "BLE not supported");
            finish();
        }



        scanBtn = findViewById(R.id.scanBtn);
        dummy = findViewById(R.id.dummy);
        addDummy = findViewById(R.id.addDummy);
        historyBtn = findViewById(R.id.historyBtn);
        workerID = findViewById(R.id.workerID);
        rssiValue = findViewById(R.id.rssiValue);


        final ConstraintLayout dummyForm = findViewById(R.id.dummyForm);

        scanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!BA.isEnabled()){
                    Intent s = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(s, REQUEST_ENABLE_BT);
                }

                scanBtn.setVisibility(View.GONE);
                historyBtn.setVisibility(View.GONE);
                dummyForm.setVisibility(View.VISIBLE);

            }
        });

        historyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent g = new Intent(MainActivity.this, ReportActivity.class);
                startActivity(g);
            }
        });

        addDummy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ID = String.valueOf(workerID.getText());
                rssi = Integer.parseInt(String.valueOf(rssiValue.getText()));
                dh.AddDummy(ID, rssi);
            }
        });

        dummy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(MainActivity.this, MissingList.class);
                startActivity(j);
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}