package com.example.proyectoprueba.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ficha {
    @SerializedName("idFichaClinica")
    @Expose
    private Integer idFichaClinica;
    @SerializedName("observacion")
    @Expose
    private String observacion;
    @SerializedName("motivoConsulta")
    @Expose
    private String motivoConsulta;
    @SerializedName("diagnostico")
    @Expose
    private String diagnostico;
    @SerializedName("idEmpleado")
    @Expose
    private Paciente idEmpleado;
    @SerializedName("idCliente")
    @Expose
    private Paciente idCliente;
    @SerializedName("idTipoProducto")
    @Expose
    private TipoProducto idTipoProducto;

    public Ficha() {
    }

    public Integer getIdFichaClinica() {
        return idFichaClinica;
    }

    public void setIdFichaClinica(Integer idFichaClinica) {
        this.idFichaClinica = idFichaClinica;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getMotivoConsulta() {
        return motivoConsulta;
    }

    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public Paciente getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Paciente idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Paciente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Paciente idCliente) {
        this.idCliente = idCliente;
    }

    public TipoProducto getIdTipoProducto() {
        return idTipoProducto;
    }

    public void setIdTipoProducto(TipoProducto idTipoProducto) {
        this.idTipoProducto = idTipoProducto;
    }

    @Override
    public String toString() {
        return "Ficha{" +
                "idFichaClinica=" + idFichaClinica +
                ", observacion='" + observacion + '\'' +
                ", motivoConsulta='" + motivoConsulta + '\'' +
                ", diagnostico='" + diagnostico + '\'' +
                ", idEmpleado=" + idEmpleado +
                ", idCliente=" + idCliente +
                ", idTipoProducto=" + idTipoProducto +
                '}';
    }
}
