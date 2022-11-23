package com.wisdomleaftest.screens.main;

import static androidx.constraintlayout.widget.Constraints.TAG;

import android.util.Log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wisdomleaftest.apipresenter.APIResponsePresenter;
import com.wisdomleaftest.apipresenter.ApiReqType;
import com.wisdomleaftest.interfaces.IRequestInterface;
import com.wisdomleaftest.interfaces.IResponseInterface;
import com.wisdomleaftest.screens.main.model.Datum;
import com.wisdomleaftest.singelton.AppController;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import retrofit2.Response;

public class ListPresenter implements IListPresenter, IResponseInterface {
    private final IRequestInterface iRequestInterface;
    private final IListView iListView;

    public ListPresenter(IListView iListView) {
        this.iRequestInterface = new APIResponsePresenter(this);
        this.iListView = iListView;
    }

    @Override
    public void onResponseSuccess(Response response, String reqType) throws JSONException, IOException {
        if (response != null) {
            if (reqType.equalsIgnoreCase(ApiReqType.List)) {

                Object model = response.body();
                iListView.setList(model);
            }
        }
    }

    @Override
    public void onResponseFailure(String responseError) {
        Log.i(TAG, "responseerror: " + responseError);
    }

    @Override
    public void getList(int pageNo, int limit) {
        iRequestInterface.CallApi(AppController.getInstance().service.list(pageNo, limit), ApiReqType.List);

    }
}
