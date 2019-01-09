package com.lucianpiros.bakingapp.data.retrofit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit client.
 *
 * @author Lucian Piros
 * @version 1.0
 */
public class RetrofitClient {

    /**
     * Retrofit
     */
    private static Retrofit retrofit = null;

    /**
     * Setup Retrofit interface
     *
     * @param url - base URl to be used
     * @return - setup Retrofit interface
     */
    public static Retrofit getClient(String url) {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit;
    }
}
