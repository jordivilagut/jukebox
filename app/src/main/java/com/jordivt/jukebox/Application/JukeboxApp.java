package com.jordivt.jukebox.Application;

import android.app.Application;

import com.jordivt.jukebox.Service.ApiService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JukeboxApp extends Application {

    private static final String ITUNES_HOST = "https://itunes.apple.com";

    private static JukeboxApp instance;
    private ApiService apiService;


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initApiService();
    }

    private void initApiService() {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .readTimeout(1, TimeUnit.MINUTES)
                .connectTimeout(1, TimeUnit.MINUTES)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ITUNES_HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient).build();

        apiService =  retrofit.create(ApiService.class);
    }

    public static JukeboxApp get() {
        return instance;
    }

    public ApiService getApiService() {
        return apiService;
    }
}
