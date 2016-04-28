package com.assesment.fragments;


import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.assesment.PresenterImplementation.NewsListPresenter;
import com.assesment.PresenterImplementation.ServiceImpl;
import com.assesment.interfaces.NewsListView;
import com.assesment.magazineapp.R;
import com.assesment.model.Facts;
import com.assesment.model.Row;
import com.assesment.util.ConnectionLookup;
import com.assesment.util.Log;
import java.util.ArrayList;
import java.util.List;


/*
NewsListFragment used to list the facts from the webservice
It contains SwipeRefreshLayout used to refresh the content or data by pulling down from the top

and RecyclerView used to list the rows

Its implemented NewsListView to get the service call backs and view functionaries
We have Presenter NewsListPresenter used to communicate with view and the model

We have ServiceImpl class for calling the webservice


 */

public class NewsListFragment extends Fragment implements NewsListView {

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private List<Row> dataSet=new ArrayList<>();
    Facts response=null;
    private View mView;
    NewsListPresenter presenter;
    RecyclerView.LayoutManager mLayoutManager;
      public NewsListFragment() {
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Handling the orientation change

        if(null!=savedInstanceState){
            if(null!=savedInstanceState.getParcelable("FACTS")){
                response=savedInstanceState.getParcelable("FACTS");
                dataSet=response.getRows();
                getActivity().setTitle(response.getTitle());
            }
        }
    }

    // NewInstance for the NewsListFragment
    public static NewsListFragment newInstance() {
        NewsListFragment fragment = new NewsListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_news_list, container, false);
        initViewControls();

        // Initializing the presenter with the context and SeviceImpl which is used to call the webservice
        presenter=new NewsListPresenter(NewsListFragment.this,new ServiceImpl());
        // Ask presenter to set th recyclerview with the default values
        presenter.setRecyclerView(getActivity(),mRecyclerView,mLayoutManager,dataSet);
        // Setting up the SwipeRefreshLayout
        presenter.setSwipeRefreshLayout(mSwipeRefreshLayout);


        // Checking the internet connection before calling the webservice


        if(null==response) {

            if (ConnectionLookup.isConnectingToInternet(getActivity())) {
                // Ask presenter to load the facts from the webservice
                presenter.startInitialLoading();
            } else {
                Toast.makeText(getActivity(), getResources().getString(R.string.connection_error), Toast.LENGTH_SHORT).show();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }

        return mView;
    }



    // storing the values for handling configuration change
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable("FACTS", (Parcelable) response);
    }

    //Initializing the view controls
    private void initViewControls() {
        mLayoutManager=new LinearLayoutManager(getActivity());
        mRecyclerView= (RecyclerView) mView.findViewById(R.id.itemsRecyclerView);
        mSwipeRefreshLayout= (SwipeRefreshLayout) mView.findViewById(R.id.swipeRefreshLayout);
    }


    // Success callback from the presenter
    @Override
    public void onSuccess(Facts response) {

        this.response=response;
        // Ask presenter to load the new content to the recyclerview
        presenter.loadNewsRecyclerView(response.getRows());
        // Setting the activity tittle from the webservice response
        getActivity().setTitle(response.getTitle());

    }

    // Error call back from the presenter
    @Override
    public void onError() {

        Toast.makeText(getActivity(),getResources().getString(R.string.service_error),Toast.LENGTH_SHORT).show();
        mSwipeRefreshLayout.setRefreshing(false);
    }


//Network error callback from the presenter

    @Override
    public void sendConnectionError() {
        Toast.makeText(getActivity(),getResources().getString(R.string.connection_error),Toast.LENGTH_SHORT).show();
    }



}
