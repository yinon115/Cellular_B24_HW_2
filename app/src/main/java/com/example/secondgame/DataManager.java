package com.example.secondgame;

import com.example.secondgame.model.ListOfResults;
import com.example.secondgame.model.Result;
import com.example.secondgame.utils.MySPV3;
import com.google.gson.Gson;

import java.util.ArrayList;

public class DataManager {

    public static ListOfResults getTopResults() {
        ListOfResults listOfResults = getResults();
        if (listOfResults != null) {
            ArrayList<Result> results = sortResults(listOfResults);
            listOfResults = new ListOfResults();
            int size = results.size();
            for (int i = 0; i < size; i++) {
                listOfResults.add(results.get(i));
                if (i == 9) break;
            }
        }
        return listOfResults;
    }

    private static ListOfResults getResults() {
        return new Gson().fromJson(MySPV3.getInstance().getString("records", ""), ListOfResults.class);
    }

    private static ArrayList<Result> sortResults(ListOfResults listOfResults) {
        listOfResults.getResults().sort((r1, r2) -> Integer.compare(r2.getScore(), r1.getScore()));
        return listOfResults.getResults();
    }
}