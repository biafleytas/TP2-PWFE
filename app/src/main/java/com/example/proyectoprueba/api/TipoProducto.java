package com.example.proyectoprueba.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TipoProducto {
    @SerializedName("idTipoProducto")
    @Expose
    private Integer idTipoProducto;
    @SerializedName("descripcion")
    @Expose
    private String descripcion;

    public TipoProducto() {
    }

    public Integer getIdTipoProducto() {
        return idTipoProducto;
    }

    public void setIdTipoProducto(Integer idTipoProducto) {
        this.idTipoProducto = idTipoProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "TipoProducto{" +
                "idTipoProducto=" + idTipoProducto +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
