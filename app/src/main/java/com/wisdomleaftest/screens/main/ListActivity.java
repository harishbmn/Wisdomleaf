package com.wisdomleaftest.screens.main;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.wisdomleaftest.R;
import com.wisdomleaftest.screens.main.adapter.ListAdapter;
import com.wisdomleaftest.screens.main.model.Datum;

import java.util.List;

public class ListActivity extends AppCompatActivity implements IListView {
    RecyclerView recyclerView;
    private ListAdapter listAdapter;
    private IListPresenter iListPresenter;
    private ProgressBar loadingPB;
    int page = 0, limit = 20;
    private boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    LinearLayoutManager mLayoutManager;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recyclerView = findViewById(R.id.recyclerviewId);
        loadingPB = findViewById(R.id.idPBLoading);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_id);
        iListPresenter = new ListPresenter(this);
        initRecycler();

        iListPresenter.getList(page, limit);

        setUpPagination();
        setUpSwipeRefresh();

    }

    private void setUpSwipeRefresh() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                iListPresenter.getList(0, limit);
                swipeRefreshLayout.setRefreshing(false);
                initRecycler();
            }
        });
    }

    private void setUpPagination() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState > 0){
                    visibleItemCount = mLayoutManager.getChildCount();
                    totalItemCount = mLayoutManager.getItemCount();
                    pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();

                    if (loading){
                        if ((visibleItemCount+pastVisiblesItems) >= totalItemCount){
                            loading = false;
                            page++;
                            loadingPB.setVisibility(View.VISIBLE);
                            iListPresenter.getList(page, limit);
                            loading = true;
                        }
                    }
                }
            }
        });
    }

    private void initRecycler() {
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        listAdapter = new ListAdapter(iListPresenter, this);
        recyclerView.setAdapter(listAdapter);

    }


    @Override
    public void setList(List<Datum> model) {
        if (model !=null){
            listAdapter.setData(model);
            loadingPB.setVisibility(View.INVISIBLE);

        }
    }
}