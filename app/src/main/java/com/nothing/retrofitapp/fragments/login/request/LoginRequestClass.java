package com.nothing.retrofitapp.fragments.login.request;

import android.content.Context;

import com.nothing.retrofitapp.fragments.login.interfaces.LoginResultCore;
import com.nothing.retrofitapp.fragments.login.interfaces.LoginService;
import com.nothing.retrofitapp.fragments.login.models.LoginRequest;
import com.nothing.retrofitapp.fragments.login.models.LoginResponse;
import com.nothing.retrofitapp.fragments.login.utils.LoginValidations;
import com.nothing.retrofitapp.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginRequestClass {

    private LoginResultCore listener;
    private LoginService services;
    private Retrofit retrofit;
    private Context context;

    public LoginRequestClass(LoginResultCore listener, Context context) {
        this.context = context;
        this.listener = listener;
        this.retrofit = RetrofitClient.getRetrofitInstance();
        services = retrofit.create(LoginService.class);
    }

    public void validateData(String user, String password) {
        String resultValidation = LoginValidations.validateUserData(user, password);
        if (resultValidation.equals("SUCCESS")) {
            doLoginRequest(user, password);
        } else {
            listener.setMessage("Datos incorrectos!!!");
        }
    }

    private void doLoginRequest(String user, String password) {
        LoginRequest loginRequest = new LoginRequest(user, password);
        Call<LoginResponse> call = services.doLogin(loginRequest);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                getLoginResponse(response);
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                listener.setMessage(LoginValidations.getOnFailureResponse(t, context));
            }
        });
    }

    private void getLoginResponse(Response<LoginResponse> response) {
        if (LoginValidations.checkSuccessCode(response.code())) {
            getLoginSuccess(response);
        } else {
            listener.setMessage(LoginValidations.getErrorByStatus(response.code(), context));
        }
    }

    private void getLoginSuccess(Response<LoginResponse> response) {
        if (response != null) {
            LoginResponse loginResponse = response.body();
            if (loginResponse != null) {
                String token = loginResponse.getToken();
                listener.setMessage("SUCCESS, token -> " + token);
            } else {
                listener.setMessage("LoginResponse = null");
            }
        } else {
            listener.setMessage("error, response = null");
        }
    }

}
