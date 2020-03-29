package com.example.carloshermoso.RestClientAndroid;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RestExampleService {
        @GET("stats")
        Call<List<Stats>> listStats();

        @PUT("stats")
        public Stats updateStats(Stats s);

        public Stats addStats(String id, int puntuacion, int dias, int salud, int alimentos, int entretenimiento);
}
