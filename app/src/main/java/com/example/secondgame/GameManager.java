package com.example.secondgame;


import static com.example.secondgame.config.Config.KEY_LEFT;
import static com.example.secondgame.config.Config.KEY_RIGHT;

import androidx.appcompat.widget.AppCompatImageView;


import com.example.secondgame.model.Element;
import com.example.secondgame.model.ListOfResults;
import com.example.secondgame.model.Result;
import com.example.secondgame.model.Type;
import com.example.secondgame.utils.GPS;
import com.example.secondgame.utils.MySPV3;
import com.google.gson.Gson;



import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GameManager {

    private int life;
    private int placeOfPlayer = 2;
    private final int rows;
    private final int columns;
    private Element[][] matrix;
    private int lastIteration = -1;
    private int wrong;
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
    private int score = 0;
    private int indexCoin = -1;
    private int tick = 0;


    public GameManager(int life, int rows, int columns) {
        this.life = life;
        this.rows = rows;
        this.columns = columns;
        this.matrix = new Element[rows][columns];
    }

    public int getRandomIndex() {
        int min = 0;
        int max = columns;
        return (int) Math.floor(Math.random() * (max - min) + min);
    }

    public Element[][] getMatrix() {
        return matrix;
    }

    private void shiftMatrix() {
        for (int i = rows - 1; i >= 0; i--) {
            for (int j = 0; j < columns; j++) {
                if (i == rows - 1) {
                    if (matrix[i][j].getType() == Type.VISIBLE) {
                        matrix[i][j].setType(Type.INVISIBLE);
                        lastIteration = j;
                        indexCoin = -1;
                    }
                    else if (matrix[i][j].getType() == Type.COIN){
                        lastIteration = -1;
                        indexCoin = j;
                    }
                } else {
                    matrix[i + 1][j].setType(matrix[i][j].getType());
                }
            }
        }
    }

    public void updateTable() {
        tick();
        shiftMatrix();
        if(tick == 5){
            putRandomCoin();
            tick = 0;
        }
        else{
            putRandomObstacle();
        }
        raiseScore();
    }

    private void tick() {
        tick++;
    }

    private void raiseScore() {
        this.score += 1;
    }

    private void putRandomObstacle() {
        int randomNumber = getRandomIndex();
        for (int i = 0; i < columns; i++) {
            if (i == randomNumber) {
                matrix[0][i].setType(Type.VISIBLE);
            } else {
                matrix[0][i].setType(Type.INVISIBLE);
            }
        }
    }

    private void putRandomCoin(){
        int randomNumber = getRandomIndex();
        for (int i = 0; i < columns; i++) {
            if (i == randomNumber) {
                matrix[0][i].setType(Type.COIN);
            } else {
                matrix[0][i].setType(Type.INVISIBLE);
            }
        }
    }

    public boolean move(String move) {
        if (move.equals(KEY_LEFT)) {
            if (placeOfPlayer > 0) {
                placeOfPlayer--;
                return true;
            }
        } else if (move.equals(KEY_RIGHT)) {
            if (placeOfPlayer < columns - 1) {
                placeOfPlayer++;
                return true;
            }
        }
        return false;
    }

    public int getCurrentPlace() {
        return placeOfPlayer;
    }

    public void initMatrix(AppCompatImageView[][] matrixView) {

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = new Element()
                        .setImage(matrixView[i][j])
                        .setType(Type.INVISIBLE);
                ;
            }
        }
    }

    public boolean checkIsWrong() {
        if (placeOfPlayer == lastIteration && wrong < life) {
            wrong++;
            return true;
        }
        return false;
    }

    public boolean checkIsCoin(){
        if(placeOfPlayer == indexCoin){
            score+=10;
            return true;
        }
        return false;
    }

    public int getWrong() {
        return wrong;
    }

    public int getScore(){
        return score;
    }

    public boolean isEndGame() {
        return wrong == life;
    }

    public void saveResults() {
        ListOfResults listOfResults;
        String jsonData = MySPV3.getInstance().getString("records", "");
        listOfResults = new Gson().fromJson(jsonData, ListOfResults.class);
        if (listOfResults == null) {
            listOfResults = new ListOfResults();
        }
        listOfResults.getResults().add(createResult());
        MySPV3.getInstance().putString("records", new Gson().toJson(listOfResults));
    }

    private Result createResult() {
        return new Result()
                .setTime(dtf.format(LocalDateTime.now()))
                .setScore(score)
                .setX(GPS.getInstance().getLatitude())
                .setY(GPS.getInstance().getLongitude());
    }
}