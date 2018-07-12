package com.sistemas.tempera.Data;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jcapasix on 20/06/18.
 */

public class RetrofitClient {

    public static Retrofit getConfig() {
        return new Retrofit.Builder().baseUrl(Configuration.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}

