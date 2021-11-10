package com.example.proyectoprueba.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DatosFicha {
    @SerializedName("lista")
    @Expose
    private Ficha[] lista;
    @SerializedName("totalDatos")
    @Expose
    private Object totalDatos;

    public DatosFicha() {
    }

    public Ficha[] getLista() {
        return lista;
    }

    public void setLista(Ficha[] lista) {
        this.lista = lista;
    }

    public Object getTotalDatos() {
        return totalDatos;
    }

    public void setTotalDatos(Object totalDatos) {
        this.totalDatos = totalDatos;
    }
}
