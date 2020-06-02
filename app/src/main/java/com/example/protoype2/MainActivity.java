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

    Button scanBtn, dummy;
    EditText workerID, rssiValue;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Map<String, Object> employee = new HashMap<>();
    private BluetoothAdapter BA;
    private static final String TAG = "MainActivity";
    public static final int REQUEST_ENABLE_BT = 1;
    private String ID;
    private int rssi;



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
        workerID = findViewById(R.id.workerID);
        rssiValue = findViewById(R.id.rssiValue);


        final ConstraintLayout dummyForm = findViewById(R.id.dummyForm);


                scanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dummyForm.setVisibility(View.VISIBLE);
                scanBtn.setVisibility(View.GONE);

            }
        });
                dummy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ID = workerID.getText().toString();
                        rssi = Integer.parseInt(rssiValue.getText().toString());

                        FirebaseManager FM = new FirebaseManager(ID, rssi);
                        setContentView(R.layout.activity_missing_list);
                    }
                });


    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}