package com.avenjr.me.me.api;

public class ApiUtils {

    public static final String BASE_URL = "http://dummy.restapiexample.com/api/v1/";

    public static MeService getMeService() {
        return RetrofitClient.getClient(BASE_URL).create(MeService.class);
    }
}
