package com.wisdomleaftest.interfaces;


import com.fasterxml.jackson.core.JsonProcessingException;

import org.json.JSONException;

import java.io.IOException;

import retrofit2.Response;

public interface IResponseInterface {

    void onResponseSuccess(Response response, String reqType) throws JSONException, IOException;

    void onResponseFailure(String responseError);
}
