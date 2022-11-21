package com.wisdomleaftest.singelton;

import android.content.Context;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.wisdomleaftest.apipresenter.ApiConstants;
import com.wisdomleaftest.apipresenter.RestApi;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;


public class AppController extends MultiDexApplication {
    private static AppController mInstance;
    public static Context context;
    public RestApi service;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        context = this;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        service = retrofit.create(RestApi.class);
    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }


}
