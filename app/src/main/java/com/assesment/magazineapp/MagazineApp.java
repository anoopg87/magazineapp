package com.assesment.magazineapp;

import android.app.Application;

import com.assesment.util.Constant;

import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 *Application class which hold the Retrofit object which can be used from any part of the application
 *
 */
public class MagazineApp extends Application {

    public static MagazineApp mInstance;
    public static Retrofit mRetrofit;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance=this;
    }

    public static synchronized MagazineApp getInstance()
    {
        return mInstance;
    }

    public static Retrofit getRetrofit(){

        /**
         * Creating Retrofit object and retuning for caller
         * It take BASE_URL
         * GsonConverterFactory.create() to enable GSON convertor
         */
        mRetrofit=new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return  mRetrofit;
    }


}
