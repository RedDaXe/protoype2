package com.example.protoype2;

public class CategoryItem {

    public String name;
    public int distance;

    public CategoryItem(String name, int distance) {
        this.name = name;
        this.distance = distance;
    }

    public CategoryItem() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
