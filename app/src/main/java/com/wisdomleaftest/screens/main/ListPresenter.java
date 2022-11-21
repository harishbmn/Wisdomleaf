package com.wisdomleaftest.screens.main;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.wisdomleaftest.apipresenter.APIResponsePresenter;
import com.wisdomleaftest.apipresenter.ApiReqType;
import com.wisdomleaftest.interfaces.IRequestInterface;
import com.wisdomleaftest.interfaces.IResponseInterface;
import com.wisdomleaftest.screens.main.model.Datum;
import com.wisdomleaftest.singelton.AppController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import retrofit.Response;

public class ListPresenter implements IListPresenter, IResponseInterface {
    private final IRequestInterface iRequestInterface;
    private IListView iListView;

    public ListPresenter(IListView iListView) {
        this.iRequestInterface = new APIResponsePresenter(this);
        this.iListView = iListView;
    }

    @Override
    public void onResponseSuccess(Response response, String reqType) {

        if (response != null) {
            if (reqType.equalsIgnoreCase(ApiReqType.List)) {
                List<Datum> model = (List<Datum>) response.body();
                iListView.setList(model);
            }
        }
    }

    @Override
    public void onResponseFailure(String responseError) {

    }

    @Override
    public void getList(String pageNo, String limit) {
        iRequestInterface.CallApi(AppController.getInstance().service.list(pageNo, limit), ApiReqType.List);

    }
}
