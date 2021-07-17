package com.example.quizapp.service.rest;

import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {

    //const
    public static final String ipAndPort = "";//"ipconfig:port";
    private static String BASE_URL = "http://" + RestClient.ipAndPort;
    private static Retrofit retrofit;

    // static methods
    private static OkHttpClient createOkHttpClient(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
    }

    public static Retrofit createRetrofit() {
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(RestClient.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                    .client(createOkHttpClient())
                    .build();
        }
        return retrofit;
    }
}
