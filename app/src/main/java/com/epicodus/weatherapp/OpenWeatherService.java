package com.epicodus.weatherapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import se.akerfeldt.okhttp.signpost.SigningInterceptor;

/**
 * Created by Guest on 11/29/16.
 */
public class OpenWeatherService {
    public static final String TAG = OpenWeatherService.class.getSimpleName();

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

    public ArrayList<Weather> processResults(Response response) {
        ArrayList<Weather> weatherArrayList = new ArrayList<>();
        try {
            String jsonData = response.body().string();
            if(response.isSuccessful()) {
                JSONObject weatherJSON = new JSONObject(jsonData);
                String name = weatherJSON.getString("name");
                JSONObject weather = weatherJSON.getJSONArray("weather").getJSONObject(0);
                String description = weather.getString("description");
                JSONObject mainJSON = weatherJSON.getJSONObject("main");
                float temperature = Float.parseFloat(mainJSON.getString("temp"));

                Weather currentWeather = new Weather(name, description, temperature);
                weatherArrayList.add(currentWeather);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return weatherArrayList;
    }
}
