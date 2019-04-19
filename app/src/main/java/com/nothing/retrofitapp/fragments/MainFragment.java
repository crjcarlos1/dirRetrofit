package com.nothing.retrofitapp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nothing.retrofitapp.R;
import com.nothing.retrofitapp.fragments.login.interfaces.LoginResultCore;
import com.nothing.retrofitapp.fragments.login.request.LoginRequestClass;
import com.nothing.retrofitapp.fragments.put.interfaces.PutResultListener;
import com.nothing.retrofitapp.fragments.put.request.PutRequestClass;

public class MainFragment extends Fragment implements View.OnClickListener, LoginResultCore, PutResultListener {
    public static final String TAG = MainFragment.class.getSimpleName();

    private EditText edtEmail, edtPassword;
    private Button btnLogin, btnPutRequest,btnUploadImage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        edtPassword = (EditText) view.findViewById(R.id.edtPassword);
        edtEmail = (EditText) view.findViewById(R.id.edtEmail);
        btnLogin = (Button) view.findViewById(R.id.btnLogin);
        btnPutRequest = (Button) view.findViewById(R.id.btnPutRequest);
        btnUploadImage = (Button) view.findViewById(R.id.btnUploadImage);
        btnLogin.setOnClickListener(this);
        btnPutRequest.setOnClickListener(this);
        btnUploadImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                doLogin();
                break;
            case R.id.btnPutRequest:
                putRequest();
                break;
            case R.id.btnUploadImage:
                uploadImage();
                break;
        }
    }

    private void uploadImage() {
    }

    private void putRequest() {
        PutRequestClass putRequestClass = new PutRequestClass(this, getContext());
        putRequestClass.startPutRequest("morpheus", "zion resident");
    }

    private void doLogin() {
        LoginRequestClass loginRequestClass = new LoginRequestClass(this, getContext());
        loginRequestClass.validateData(edtEmail.getText().toString(), edtPassword.getText().toString());
    }

    @Override
    public void setMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setMessagePUT(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
