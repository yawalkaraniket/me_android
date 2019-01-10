package com.avenjr.me.me.api;

import com.avenjr.me.me.modle.Employee;
import com.avenjr.me.me.modle.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MeService {

    @GET("/employee/{id}/{password}")
    Call<User> signIn(@Path("id") String id, @Path("password") String password);

    @GET("employee/{id}")
    Call<Employee> getEmployee(@Path("id") String id);
}
