package com.example.proyectoprueba.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

public class DatosTurno {
    @SerializedName("lista")
    @Expose
    private Turno[] lista;
    @SerializedName("totalDatos")
    @Expose
    private Object totalDatos;

    public DatosTurno() {
    }

    public Turno[] getLista() {
        return lista;
    }

    public void setLista(Turno[] lista) {
        this.lista = lista;
    }

    public Object getTotalDatos() {
        return totalDatos;
    }

    public void setTotalDatos(Object totalDatos) {
        this.totalDatos = totalDatos;
    }

}
