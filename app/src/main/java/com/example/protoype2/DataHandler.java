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
import java.util.HashMap;
import java.util.Map;


public class DataHandler {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private float distance;
    Map<String, Object> employee = new HashMap<>();

    public void AddDummy (String name, int rssi){

        distance = CalculateDistance(rssi);
        employee.put("name", name);
        employee.put("distance", distance);
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

    private float CalculateDistance(int rssi) {
        BigDecimal d;
        d = new BigDecimal(Math.round(Math.pow(10, (-23 - -rssi)/(10*7.187)))).setScale(4);
        return d.floatValue();
    }
}
