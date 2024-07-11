package com.example.secondgame.activities;

import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.secondgame.DataManager;
import com.example.secondgame.callbacks.CallBack_List;
import com.example.secondgame.R;

import com.example.secondgame.callbacks.CallBack_Map;
import com.example.secondgame.fragments.Fragment_List;
import com.example.secondgame.fragments.Fragment_Map;
import com.example.secondgame.model.ListOfResults;
import com.example.secondgame.model.Result;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class RecordActivity extends AppCompatActivity {

    private Fragment_List fragment_list;
    private Fragment_Map fragment_map;

    private Button exit;
    private Button menu;

    private ImageView background;

    CallBack_List callBack_userInfo = DataManager::getTopResults;

    CallBack_Map callBack_map = map -> {
        map.clear();
        ListOfResults top10 = DataManager.getTopResults();
        if (top10 != null) {
            for (int i = 0; i < top10.size(); i++) {
                Result result = top10.get(i);

                map.addMarker(new MarkerOptions()
                        .position(new LatLng(
                                result.getX(),
                                result.getY()))
                        .title("" + i));
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        fragment_list = new Fragment_List();
        fragment_map = new Fragment_Map();

        findViews();

        initViews();

        fragment_list.setCallBack_userInfo(callBack_userInfo);
        fragment_map.setCallBack_map(callBack_map);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.panel_LAY_list, fragment_list)
                .commit();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.panel_LAY_map, fragment_map)
                .commit();
    }

    private void findViews() {
        menu = findViewById(R.id.record_BTN_menu);
        exit = findViewById(R.id.record_BTN_exit);
        background = findViewById(R.id.record_IMG_background);
    }

    private void initViews() {
        menu.setOnClickListener(view -> changeActivity());
        exit.setOnClickListener(view -> finish());
    }

    private void changeActivity() {

        finish();
        Intent intent = new Intent(RecordActivity.this, StartMenuActivity.class);
        startActivity(intent);
    }
}