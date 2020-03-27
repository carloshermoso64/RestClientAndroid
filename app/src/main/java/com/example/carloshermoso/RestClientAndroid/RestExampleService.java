package com.example.carloshermoso.RestClientAndroid;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestExampleService {
        @GET("stats")
        Call<List<Stats>> listStats();
}
