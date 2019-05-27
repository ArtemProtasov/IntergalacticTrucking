package com.example.intergalactictrucking.retrofit;

import com.example.intergalactictrucking.BuildConfig;
import com.example.intergalactictrucking.storage.Common;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import okhttp3.ConnectionSpec;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestController {

    public static AtiAPI CLIENT;
    public static Retrofit retrofit;

    public static void setUp() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Common.BASE_URL)
                .addConverterFactory(getConverterFactoryGson())
                .client(getClient()).build();
        CLIENT = retrofit.create(AtiAPI.class);
    }

    public static Converter.Factory getConverterFactoryGson() {
        return GsonConverterFactory.create(new GsonBuilder()
                .setDateFormat("yyyy-MM-dd")
                .setPrettyPrinting()
                .serializeNulls()
                .create());
    }

    public static OkHttpClient getClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .addInterceptor(getLoggingInterceptor())
                .connectionSpecs(Arrays.asList(ConnectionSpec.MODERN_TLS, ConnectionSpec.CLEARTEXT, ConnectionSpec.COMPATIBLE_TLS))
                .addInterceptor(getInceptorRequestHeader())
                .build();
    }

    public static Interceptor getInceptorRequestHeader() {
        return chain -> {
            Request request = chain.request().newBuilder()
                    .addHeader("Authorization", "Authorization KEY").build();
            return chain.proceed(request);
        };
    }

    public static HttpLoggingInterceptor getLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(getInterceptorLevel());
        return httpLoggingInterceptor;
    }

    public static HttpLoggingInterceptor.Level getInterceptorLevel() {
        if (BuildConfig.DEBUG) return HttpLoggingInterceptor.Level.BODY;
        else return HttpLoggingInterceptor.Level.NONE;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

}
