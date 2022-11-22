package com.wisdomleaftest.apipresenter;

import com.wisdomleaftest.screens.main.model.Datum;
import com.wisdomleaftest.screens.main.model.ListModel;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Query;

public interface RestApi {

    @Headers("Content-Type: application/json")
    @GET(ApiConstants.List)
    Call<List<Datum>> list(@Query("page") int pageNo, @Query("limit") int limit);

   }
