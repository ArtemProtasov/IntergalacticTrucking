package com.example.intergalactictrucking.utils;

import android.app.Activity;

import com.example.intergalactictrucking.retrofit.RestController;
import com.example.intergalactictrucking.retrofit.response.ApiBasic;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

public class ErrorUtils {

    public static ApiBasic parseError(Response<?> response, Activity a) {
        Converter<ResponseBody, ApiBasic> converter =
                RestController.retrofit.responseBodyConverter(ApiBasic.class, new Annotation[0]);

        ApiBasic error = null;
        try {
            error = converter.convert(response.errorBody());
        } catch (IOException e) {
            return new ApiBasic();
        }

        return error;
    }

    public static ApiBasic parseError(Response<?> response) {
        Converter<ResponseBody, ApiBasic> converter =
                RestController.retrofit.responseBodyConverter(ApiBasic.class, new Annotation[0]);

        ApiBasic error = null;

        try {
            error = converter.convert(response.errorBody());
        } catch (IOException e) {
            return new ApiBasic();
        }

        return error;
    }

    public static ApiBasic parseError(String Throwable) {

        ApiBasic error = new ApiBasic();

        if (Throwable.contains("Failed to connect")) {
            error.setMessage("Please check your internet connection");
        } else if (Throwable.contains("SocketException")) {
            error.setMessage("Please check your internet connection");
        } else {
            error.setMessage("Please check your internet connection");
        }
        error.setMessage(Throwable);


        return error;
    }
}
