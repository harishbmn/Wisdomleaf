package com.wisdomleaftest.apipresenter;

import com.wisdomleaftest.screens.main.model.Datum;
import com.wisdomleaftest.screens.main.model.ListModel;

import java.util.List;
import java.util.Objects;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;


public interface RestApi {

    @Headers("Content-Type: application/json")
    @GET(ApiConstants.List)
    Call<Object> list(@Query("page") int pageNo, @Query("limit") int limit);

   }
