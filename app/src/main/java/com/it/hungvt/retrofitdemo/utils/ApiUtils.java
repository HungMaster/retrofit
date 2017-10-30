package com.it.hungvt.retrofitdemo.utils;

import com.it.hungvt.retrofitdemo.data.remote.RetrofitClient;
import com.it.hungvt.retrofitdemo.data.remote.SOService;

/**
 * Created by Administrator on 10/28/2017.
 */

public class ApiUtils {
    public static final String BASE_URL = "https://api.stackexchange.com/2.2";
    public static SOService getSoService(){
        return RetrofitClient.getClient(BASE_URL).create(SOService.class);
    }
}
