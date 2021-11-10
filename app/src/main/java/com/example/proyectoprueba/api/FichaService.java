package com.example.proyectoprueba.api;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface FichaService {
    @Headers({
            "Accept: application/json"
    })
    @GET("fichaClinica")
    Call<DatosFicha> getFichas();

    @GET("fichaClinica")
    Call<DatosFicha> getEmpleado(@QueryMap Map<String, String> filtros);

    @GET("fichaClinica")
    Call<DatosFicha> getPaciente(@QueryMap Map<String, String> filtros);

    @GET("fichaClinica")
    Call<DatosFicha> getPeriodo(@QueryMap Map<String, String> filtros);
}
