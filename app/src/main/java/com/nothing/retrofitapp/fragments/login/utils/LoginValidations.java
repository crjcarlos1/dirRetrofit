package com.nothing.retrofitapp.fragments.login.utils;

import android.content.Context;

import com.nothing.retrofitapp.retrofit.RetrofitConstants;

import java.io.IOException;

public class LoginValidations {
    public static String validateUserData(String user, String password) {
        if (user == null || user.length() == 0 || password == null || password.length() == 0) {
            return "FAIL";
        } else {
            return "SUCCESS";
        }
    }

    public static String getOnFailureResponse(Throwable t, Context context) {
        if (t instanceof IOException) {
            return "Error, verifica tu conexión a internet";
        } else if (t instanceof IllegalStateException) {
            return "Error, la respuesta contiene un formato que no se puede leer";
        } else {
            return "Ocurrio un error desconocido";
        }
    }

    /**
     * Validate responseCode
     *
     * @param responseCode
     * @return
     */
    public static boolean checkSuccessCode(int responseCode) {
        if (responseCode == RetrofitConstants.SUCCESS_CODE) {
            return true;
        } else {
            return false;
        }
    }

    public static String getErrorByStatus(int responseCode, Context context) {
        String error;
        if (responseCode == RetrofitConstants.BAD_CODE) {
            error = "Error, la solicitud tiene sintaxis erronea";
        } else if (responseCode == RetrofitConstants.UNAUTHORIZED_CODE) {
            error = "Error, solicitud no autorizada";
        } else if (responseCode == RetrofitConstants.NOT_FOUND_CODE) {
            error = "Error, recurso no encontrado";
        } else if (responseCode == RetrofitConstants.INTERNAL_SERVER_ERROR_CODE) {
            error = "Error, se ha producido un error en la conexión con el servidor, intente más tarde";
        } else {
            error = "Error desconocido";
        }
        return error;
    }

}
