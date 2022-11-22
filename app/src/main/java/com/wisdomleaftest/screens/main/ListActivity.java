package com.wisdomleaftest.screens.main;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

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
    int page = 0, limit = 20;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    LinearLayoutManager mLayoutManager;
    SwipeRefreshLayout swipeRefreshLayout;
    private ListAdapter listAdapter;
    private IListPresenter iListPresenter;
    private ProgressBar loadingPB;
    private boolean loading = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recyclerView = findViewById(R.id.recyclerviewId);
        loadingPB = findViewById(R.id.idPBLoading);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_id);
        iListPresenter = new ListPresenter(this);
        initRecycler();

        callApi();
        setUpPagination();
        setUpSwipeRefresh();

    }

    private void callApi() {
        if (isConnected()) {
            iListPresenter.getList(page, limit);
        } else {
            loadingPB.setVisibility(View.INVISIBLE);
            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }

    private void setUpSwipeRefresh() {
        swipeRefreshLayout.setOnRefreshListener(() -> {
            if (isConnected()) {
                iListPresenter.getList(0, limit);
            } else {
                loadingPB.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
            }

            swipeRefreshLayout.setRefreshing(false);
            initRecycler();
        });
    }

    private void setUpPagination() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState > 0) {
                    visibleItemCount = mLayoutManager.getChildCount();
                    totalItemCount = mLayoutManager.getItemCount();
                    pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();

                    if (loading) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            loading = false;
                            page++;
                            loadingPB.setVisibility(View.VISIBLE);
                            callApi();
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
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        listAdapter = new ListAdapter(iListPresenter, this);
        recyclerView.setAdapter(listAdapter);

    }

    public boolean isConnected() {
        boolean connected = false;
        try {
            ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cm.getActiveNetworkInfo();
            connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
            return connected;
        } catch (Exception e) {
            Log.e("Connectivity Exception", e.getMessage());
        }
        return connected;
    }


    @Override
    public void setList(List<Datum> model) {
        if (model != null) {
            listAdapter.setData(model);
            loadingPB.setVisibility(View.INVISIBLE);

        }
    }
}