package com.example.proyectoprueba.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datos {
    @SerializedName("lista")
    @Expose
    private Paciente[] lista;
    @SerializedName("totalDatos")
    @Expose
    private Object totalDatos;

    public Datos() {
    }

    public Paciente[] getLista() {
        return lista;
    }

    public void setLista(Paciente[] lista) {
        this.lista = lista;
    }

    public Object getTotalDatos() {
        return totalDatos;
    }

    public void setTotalDatos(Object totalDatos) {
        this.totalDatos = totalDatos;
    }
}
