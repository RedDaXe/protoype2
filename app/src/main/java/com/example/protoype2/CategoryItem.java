package com.example.protoype2;

public class CategoryItem {

    private String name;
    private int distance;

    public CategoryItem() {
    }


    public CategoryItem(String name, int distance) {
        this.name = name;
        this.distance = distance;
    }


    public String getName() {
        return name;
    }

    public int getDistance() {
        return distance;
    }


}
