package com.uece.br.mobuece.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitFactory {
    Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    public Retrofit GetRetrofit(){
        return new Retrofit.Builder()
                .baseUrl("https://ws.uece.br/wsuece/rest/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }
}
