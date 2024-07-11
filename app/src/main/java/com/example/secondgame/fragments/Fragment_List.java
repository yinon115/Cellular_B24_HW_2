package com.example.secondgame.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.secondgame.callbacks.CallBack_List;
import com.example.secondgame.R;
import com.example.secondgame.model.ListOfResults;
import com.example.secondgame.model.Result;


public class Fragment_List extends Fragment {

    private ListView listView;
    private CallBack_List callBack_userInfo;

    public void setCallBack_userInfo(CallBack_List callBack_userInfo) {
        this.callBack_userInfo = callBack_userInfo;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        findViews(view);
        initViews();
        return view;
    }

    private void initViews() {
        ListOfResults data = callBack_userInfo.getResults();
        if (data != null) {
            ArrayAdapter<Result> adapter = new ArrayAdapter<Result>(getActivity(), android.R.layout.simple_list_item_1, data.getResults());
            listView.setAdapter(adapter);
        }

    }

    private void findViews(View view) {
        listView = view.findViewById(R.id.fragmentList_top10);
    }
}