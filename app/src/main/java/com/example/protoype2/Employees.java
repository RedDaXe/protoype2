package com.example.protoype2;

public class Employees {

    private String name;
    private int distance;

    public Employees() {
    }


    public Employees(String name, int distance) {
        this.name = name;
        this.distance = distance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public int getDistance() {
        return distance;
    }
}

