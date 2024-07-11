package com.example.secondgame.model;

public class Result {
    private String time;
    private int score;

    private double x;
    private double y;

    public Result() {
    }

    public String getTime() {
        return time;
    }

    public Result setTime(String time) {
        this.time = time;
        return this;
    }

    public int getScore() {
        return score;
    }

    public Result setScore(int score) {
        this.score = score;
        return this;
    }

    public double getX() {
        return x;
    }

    public Result setX(double x) {
        this.x = x;
        return this;
    }

    public double getY() {
        return y;
    }

    public Result setY(double y) {
        this.y = y;
        return this;
    }

    @Override
    public String toString() {
        return "Time=" + time + "\n" + "latitude: " + x + "\n" + " longitude: " + y + "\n"
                + "Score= " + score;
    }
}