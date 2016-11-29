package com.epicodus.weatherapp;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import se.akerfeldt.okhttp.signpost.SigningInterceptor;

/**
 * Created by Guest on 11/29/16.
 */
public class OpenWeatherService {

    public static void getCurrentWeather(String location, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder().build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.OPENWEATHER_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.OPENWEATHER_LOCATION_QUERY, location);
        urlBuilder.addQueryParameter(Constants.OPENWEATHER_APPID_QUERY, Constants.OPENWEATHER_KEY);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }
}
