package com.epicodus.weatherapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WeatherActivity extends AppCompatActivity {
    public static final String TAG = WeatherActivity.class.getSimpleName();
    public ArrayList<Weather> mWeather = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        location = location + ",us";
        getWeather(location);
    }

    private void getWeather(String location) {

        final OpenWeatherService openWeatherService = new OpenWeatherService();
        openWeatherService.getCurrentWeather(location, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //try {
                    //String jsonData = response.body().string();
                    if(response.isSuccessful()) {
                        mWeather = openWeatherService.processResults(response);
                        Log.d(TAG, mWeather.toString());
                    }
               //} catch (IOException e) {
               //     e.printStackTrace();
               // }
            }
        });
    }
}
