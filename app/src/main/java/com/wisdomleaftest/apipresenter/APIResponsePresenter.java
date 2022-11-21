package com.wisdomleaftest.apipresenter;

import com.wisdomleaftest.interfaces.IRequestInterface;
import com.wisdomleaftest.interfaces.IResponseInterface;

import java.net.ConnectException;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class APIResponsePresenter implements IRequestInterface {
    IResponseInterface iResponseInterface;

    public APIResponsePresenter(IResponseInterface iResponseInterface) {
        this.iResponseInterface = iResponseInterface;
    }

    @Override
    public void CallApi(Call call, final String reqType) {
       call.enqueue(new Callback() {
           @Override
           public void onResponse(Response response, Retrofit retrofit) {
               iResponseInterface.onResponseSuccess(response, reqType);
           }

           @Override
           public void onFailure(Throwable t) {
               if (t instanceof ConnectException) {
                   iResponseInterface.onResponseFailure("No Internet Connection");
               } else {
                   t.printStackTrace();
                   iResponseInterface.onResponseFailure("Response Failed");
               }
           }
       });
    }

}
