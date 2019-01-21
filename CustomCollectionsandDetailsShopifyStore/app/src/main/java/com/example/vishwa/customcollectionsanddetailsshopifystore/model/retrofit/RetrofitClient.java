package com.example.vishwa.customcollectionsanddetailsshopifystore.model.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Vishwa Patel
 */

public class RetrofitClient {

    private static Retrofit retrofitClient;
    private static final String BASE_URL = "https://shopicruit.myshopify.com";

    public static Retrofit getRetrofitClient(){
        if(retrofitClient == null) {

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor).build();

            Gson gson = new GsonBuilder().setLenient()
                    .create();

            retrofitClient = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }

        return retrofitClient;
    }
}
