package com.example.secondgame.model;

import java.util.ArrayList;

public class ListOfResults {

    private ArrayList<Result> results;

    public ListOfResults() {
        results = new ArrayList<>();
    }
    ;

    public ArrayList<Result> getResults() {
        return results;
    }

    public ListOfResults setResults(ArrayList<Result> results) {
        this.results = results;
        return this;
    }

    public int size() {
        return results.size();
    }

    public void add(Result result){
        results.add(result);
    }

    public Result get(int index) {
        return results.get(index);
    }
}