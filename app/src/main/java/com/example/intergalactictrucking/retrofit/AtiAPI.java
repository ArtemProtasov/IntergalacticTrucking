package com.example.intergalactictrucking.retrofit;

import com.example.intergalactictrucking.storage.Common;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AtiAPI {

    @GET(Common.BASE_URL + "/api")
    Call<Object> getTestObject();

}
