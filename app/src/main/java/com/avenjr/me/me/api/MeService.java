package com.avenjr.me.me.api;

import com.avenjr.me.me.modle.Employee;
import com.avenjr.me.me.modle.User;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Streaming;

public interface MeService {

    @GET("/employee/{id}/{password}")
    Call<User> signIn(@Path("id") String id, @Path("password") String password);

    @GET("employee/{id}")
    Call<Employee> getEmployee(@Path("id") String id);

    @GET("files/Node-Android-Chat.zip")
    @Streaming
    Call<ResponseBody> downloadFile();

}
