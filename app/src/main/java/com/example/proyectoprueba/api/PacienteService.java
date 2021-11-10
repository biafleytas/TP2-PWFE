package com.example.proyectoprueba.api;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface PacienteService {
    @Headers({
            "Accept: application/json"
    })
    @GET("persona")
    Call<Datos> getPacientes();

    @GET("persona?ejemplo=%7B%22soloUsuariosDelSistema%22%3Atrue%7D")
    Call<Datos> getUsuarioDelSistema();

    @GET("persona")
    Call<Datos> getPacienteNombre(@QueryMap Map<String, String> filtros);

    @GET("persona")
    Call<Datos> getPacienteApellido(@QueryMap Map<String, String> filtros);

    @GET("persona/{id}/agenda")
    Call<Turno[]> getAgenda(@Path("id") String id, @QueryMap Map<String, String> filtros);
}
