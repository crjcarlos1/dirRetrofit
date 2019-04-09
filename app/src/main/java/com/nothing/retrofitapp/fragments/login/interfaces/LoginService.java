package com.nothing.retrofitapp.fragments.login.interfaces;

import com.nothing.retrofitapp.fragments.login.models.LoginRequest;
import com.nothing.retrofitapp.fragments.login.models.LoginResponse;
import com.nothing.retrofitapp.retrofit.Endpoints;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {

    /**
     * @param loginRequest
     * @return
     */
    @POST(Endpoints.LOGIN)
    Call<LoginResponse> doLogin(@Body LoginRequest loginRequest);

}
