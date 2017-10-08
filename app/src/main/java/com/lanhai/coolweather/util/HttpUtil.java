package com.lanhai.coolweather.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by ${Zhanglinlin} on 2017/10/8 0008.
 */

public class HttpUtil {

    public static void sendOkHttpReques(String address, okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request  = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }
}
