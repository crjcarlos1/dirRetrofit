package com.nothing.retrofitapp.fragments.put.interfaces;

import com.nothing.retrofitapp.fragments.put.models.PutRequest;
import com.nothing.retrofitapp.fragments.put.models.PutResponse;
import com.nothing.retrofitapp.retrofit.Endpoints;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PUT;

public interface PutServices {

    @PUT(Endpoints.PUT_JOB)
    Call<PutResponse> put(@Body PutRequest putRequest);

}
