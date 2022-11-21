package com.wisdomleaftest.screens.main;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wisdomleaftest.R;
import com.wisdomleaftest.screens.main.adapter.ListAdapter;
import com.wisdomleaftest.screens.main.model.Datum;
import com.wisdomleaftest.screens.main.model.ListModel;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity implements IListView {
    RecyclerView recyclerView;
    private ListAdapter listAdapter;
    private IListPresenter iListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);



        recyclerView = findViewById(R.id.recyclerviewId);
        iListPresenter = new ListPresenter(this);
        iListPresenter.getList("1","20");
        initRecycler();
    }

    private void initRecycler() {
        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        listAdapter = new ListAdapter(iListPresenter, this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(listAdapter);
    }



    @Override
    public void setList(List<Datum> model) {
        if (model !=null){
            listAdapter.setData(model);
        }
    }
}