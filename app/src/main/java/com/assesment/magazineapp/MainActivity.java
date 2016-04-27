package com.assesment.magazineapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.assesment.fragments.NewsListFragment;

public class MainActivity extends AppCompatActivity  {

public static String TAG="MainActivity";

    /**
     * MainActivity which loads the NewsListFragment
     *
     */


    Toolbar toolbar;
    FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);
        loadNewsFragment();
    }

    private void loadNewsFragment() {

        mFragmentManager=getSupportFragmentManager();
        FragmentTransaction mFragmentTransaction=mFragmentManager.beginTransaction();
        Fragment mFragment=NewsListFragment.newInstance();
        mFragmentTransaction.replace(R.id.fragmentLoadingspace, mFragment);
        mFragmentTransaction.addToBackStack(mFragment.getClass().getName());
        mFragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Fragment fragment=mFragmentManager.findFragmentById(R.id.fragmentLoadingspace);
        if(fragment instanceof NewsListFragment){
            finish();
        }
    }
}
