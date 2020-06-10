package com.example.protoype2;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.api.Context;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.internal.$Gson$Preconditions;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;


public class DataHandler {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    Map<String, Object> employee = new HashMap<>();
    Map<String, Object> history = new HashMap<>();

    public void AddDummy (String name, int rssi){

        employee.put("name", name);
        employee.put("distance", rssi);
        db.collection("employees")
                .add(employee)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("MainActivity", "Employee added in " + documentReference.getId());

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("MainActivity", "Error occured", e);

                    }
                });
    }

    public void AddHistory(int alive, int dead, String date){
        history.put("alive", alive);
        history.put("dead", dead);
        history.put("date", date);
        db.collection("history")
                .add(history)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("ReportActivity", "History Added " + documentReference.getId());

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("ReportActivity", "Error occured", e);

                    }
                });
    }


    public float CalculateDistance(int rssi) {
        float f;
        DecimalFormat df = new DecimalFormat("#.000");
        f = (float)Math.pow(10.00, (-23.00 - -(float)rssi)/(10.00*7.1870));
        return Float.valueOf(df.format(f));
    }
}
