package com.avenjr.me.me.api;

public class ApiUtils {

    public static final String BASE_URL = "https://download.learn2crack.com/";

    public static MeService getMeService() {
        return RetrofitClient.getClient(BASE_URL).create(MeService.class);
    }
}
