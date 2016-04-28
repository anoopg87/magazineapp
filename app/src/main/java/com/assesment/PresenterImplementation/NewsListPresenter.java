package com.assesment.PresenterImplementation;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import com.assesment.adapter.FactsAdapter;
import com.assesment.interfaces.NewsListView;
import com.assesment.magazineapp.MagazineApp;
import com.assesment.model.Row;
import com.assesment.util.ConnectionLookup;
import java.util.List;

/**
 *
 *
 * Presenter which handle the NewsListFragment operations
 *
 * Constructor take two parameter  NewsListView interface and  ServiceImpl class
 *  NewsListView interface handles the callbacks to the fragments
 *  ServiceImpl handles the webservice integration
 *
 *
 *
 *
 */


public class NewsListPresenter  {

    private NewsListView view;
    private ServiceImpl service;
    RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    List<Row> dataSet;
    SwipeRefreshLayout mSwipeRefreshLayout;
    private FactsAdapter mFactsAdapter;

    public NewsListPresenter(NewsListView view, ServiceImpl service) {
        this.view = view;
        this.service = service;

    }

    public void invokeFactApi(){

        // invoking teh webservice by passing the interface so that it can respond to the fragment directly
        service.invokeFactsApi(view);
    }


    public void loadNewsRecyclerView(List<Row> response) {

        // Function to load the data to the recycler view after successful webservice call
        mFactsAdapter.addFacts(response);
        // setting the refresh object to false to hide the progressbar
        mSwipeRefreshLayout.setRefreshing(false);

    }


    public void setSwipeRefreshLayout( SwipeRefreshLayout swipeRefreshLayout) {

        // initializing the swipeRefreshLayout with OnRefreshListener
        this.mSwipeRefreshLayout = swipeRefreshLayout;
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(ConnectionLookup.isConnectingToInternet(MagazineApp.getInstance().getApplicationContext())) {

                    // Checking the internet connection and invokeFactApi method to load the data
                    invokeFactApi();
                }else{
                    // sending the error message to fragment if the connection fails
                    view.sendConnectionError();
                     mSwipeRefreshLayout.setRefreshing(false);
                }
            }
        });
    }

    public void setRecyclerView(Context context,RecyclerView mRecyclerView, RecyclerView.LayoutManager mLayoutManager, List<Row> dataSet) {

// Setting the recyclerview with default values for initial setup

        this.mRecyclerView = mRecyclerView;
        this.mLayoutManager = mLayoutManager;
        this.dataSet = dataSet;
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mFactsAdapter=new FactsAdapter(context,dataSet);
        mRecyclerView.setAdapter(mFactsAdapter);

    }


    public void startInitialLoading() {

        // Function to load the data from the webservice on the launch of the application
        // it used mSwipeRefreshLayout method to show the progressbar while loading
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
                invokeFactApi();
            }
        });

    }
}
