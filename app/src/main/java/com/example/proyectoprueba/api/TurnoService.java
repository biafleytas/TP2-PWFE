package com.example.proyectoprueba.api;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface TurnoService {
    @Headers({
            "Accept: application/json"
    })
    @GET("reserva")
    Call<DatosTurno> getTurnos(@QueryMap Map<String, String> filtros);

    @GET("reserva")
    Call<DatosTurno> getReservaEmpleado(@QueryMap Map<String, String> filtros);

    @GET("reserva")
    Call<DatosTurno> getReservaPaciente(@QueryMap Map<String, String> filtros);

    @GET("reserva")
    Call<DatosTurno> getReservaDia(@QueryMap Map<String, String> filtros);
}
