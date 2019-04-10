package com.nothing.retrofitapp.fragments.put.request;

import android.content.Context;

import com.nothing.retrofitapp.fragments.login.utils.LoginValidations;
import com.nothing.retrofitapp.fragments.put.interfaces.PutResultListener;
import com.nothing.retrofitapp.fragments.put.interfaces.PutServices;
import com.nothing.retrofitapp.fragments.put.models.PutRequest;
import com.nothing.retrofitapp.fragments.put.models.PutResponse;
import com.nothing.retrofitapp.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PutRequestClass {

    private PutResultListener listener;
    private PutServices services;
    private Retrofit retrofit;
    private Context context;

    public PutRequestClass(PutResultListener listener, Context context) {
        this.listener = listener;
        this.context = context;
        retrofit = RetrofitClient.getRetrofitInstance();
        services = retrofit.create(PutServices.class);
    }

    public void startPutRequest(String name, String job) {
        PutRequest request = new PutRequest(name, job);
        Call<PutResponse> call = services.put(request);

        call.enqueue(new Callback<PutResponse>() {
            @Override
            public void onResponse(Call<PutResponse> call, Response<PutResponse> response) {
                getPutResponse(response);
            }

            @Override
            public void onFailure(Call<PutResponse> call, Throwable t) {
                listener.setMessagePUT((LoginValidations.getOnFailureResponse(t, context)));
            }
        });
    }

    private void getPutResponse(Response<PutResponse> response) {
        if (LoginValidations.checkSuccessCode(response.code())) {
            getPutSuccess(response);
        } else {
            listener.setMessagePUT(LoginValidations.getErrorByStatus(response.code(), context));
        }
    }

    private void getPutSuccess(Response<PutResponse> response) {
        if (response != null) {
            PutResponse putResponse = response.body();
            if (putResponse != null) {
                listener.setMessagePUT("SUCCESS --> " + putResponse.getUpdatedAt());
            } else {
                listener.setMessagePUT("putResponse = null");
            }
        } else {
            listener.setMessagePUT("response = null");
        }
    }


}
