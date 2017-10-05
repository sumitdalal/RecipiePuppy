package com.recipiepuppy.recipiepuppy.services;


import com.recipiepuppy.recipiepuppy.BuildConfig;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sumit on 27/9/17.
 */

public class ConnectionFactory {
    private static ConnectionFactory connectionFactory;
    private Retrofit.Builder builder;

    private ConnectionFactory() {

    }

    public static ConnectionFactory getInstance() {
        if (connectionFactory == null) {
            connectionFactory = new ConnectionFactory();
            return connectionFactory;
        } else
            return connectionFactory;
    }

    private OkHttpClient getOkHttpClient() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addNetworkInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder()
                        .build();
                return chain.proceed(request);
            }
        });

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.interceptors().add(logging);
        return httpClient.build();
    }

    private Retrofit getRetroBuilder() {
        if (builder == null)
            builder = new Retrofit.Builder().baseUrl(BuildConfig.SERVER_URL)
                    .addConverterFactory(GsonConverterFactory.create());
        builder.client(getOkHttpClient());
        return builder.build();

    }

    public ApiService getRetroService() {
        return getRetroBuilder().create(ApiService.class);
    }

}