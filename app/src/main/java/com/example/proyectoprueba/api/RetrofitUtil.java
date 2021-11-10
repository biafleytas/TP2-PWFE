package com.example.proyectoprueba.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtil {
    private static Retrofit retrofit = null;
    private static String URL_BASE = "https://equipoyosh.com//stock-nutrinatalia/";

    public static  Retrofit getClient(String baseUrl) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }

    public static PacienteService getPacienteService(){
        return RetrofitUtil.getClient(URL_BASE).create(PacienteService.class);

    }

    public static FichaService getFichaService(){
        return RetrofitUtil.getClient(URL_BASE).create(FichaService.class);

    }

    public static TurnoService getTurnoService(){
        return RetrofitUtil.getClient(URL_BASE).create(TurnoService.class);

    }
}
