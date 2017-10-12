package com.lanhai.coolweather.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.SystemClock;
import android.preference.PreferenceManager;

import com.lanhai.coolweather.gson.Weather;
import com.lanhai.coolweather.util.HttpUtil;
import com.lanhai.coolweather.util.Utility;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class AutoUpdateService extends Service {


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        updateWeather();
        updateBingpic();
        AlarmManager alarm = (AlarmManager) getSystemService(ALARM_SERVICE);
        int anHour = 8 * 60 * 60 * 1000;// 8个小时的毫秒值
        long triggerAtTime = SystemClock.elapsedRealtime();
        Intent i = new Intent(this, AutoUpdateService.class);
        PendingIntent pendingIntent = PendingIntent.getService(this, 0, i, 0);
        alarm.cancel(pendingIntent);
        alarm.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pendingIntent);
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 更新必应每日一图
     */
    private void updateBingpic() {
        final String requestBingPic = "http://guolin.tech/api/bing_pic";
        HttpUtil.sendOkHttpReques(requestBingPic, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String bingPic = response.body().string();
                SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(AutoUpdateService.this).edit();
                edit.putString("bing_pic", bingPic);
                edit.apply();
            }
        });
    }

    /**
     * 更新天气信息
     */
    private void updateWeather() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String weatherString = preferences.getString("weather", null);
        if (weatherString != null) {
            Weather weather = Utility.handleWeatherResponse(weatherString);
            String weatherId = weather.getHeWeather5().get(0).getBasic().getId();
            String wertherUrl = "https://free-api.heweather.com/v5/weather?city=" + weatherId + "&key=96011843e2db4381a772d6dc8b33e8a0";
            HttpUtil.sendOkHttpReques(wertherUrl, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String responceText = response.body().string();
                    Weather weather = Utility.handleWeatherResponse(responceText);

                    if (weather != null && "ok".equals(weather.getHeWeather5().get(0).getStatus())) {
                        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(AutoUpdateService.this);
                        SharedPreferences.Editor edit = prefs.edit();
                        edit.putString("weather", responceText);
                        edit.apply();
                    }
                }
            });
        }
    }
}
