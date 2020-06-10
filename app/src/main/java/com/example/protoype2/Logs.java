package com.example.protoype2;

public class Logs {
    private int alive;
    private int dead;
    private String date;

    public Logs() {
    }

    public Logs(int alive, int dead, String date) {
        this.alive = alive;
        this.dead = dead;
        this.date = date;
    }

    public int getAlive() {
        return alive;
    }

    public void setAlive(int alive) {
        this.alive = alive;
    }

    public int getDead() {
        return dead;
    }

    public void setDead(int dead) {
        this.dead = dead;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
