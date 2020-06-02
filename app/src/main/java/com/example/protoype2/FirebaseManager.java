package com.example.protoype2;

public class FirebaseManager {
    private String name, workerID;
    private int rssi;

    public FirebaseManager(String workerID, int rssi) {
        this.workerID = workerID;
        this.rssi = rssi;
    }


}
