package com.example.proyectoprueba.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Turno {
    @SerializedName("idReserva")
    @Expose
    private Integer idReserva;
    @SerializedName("fechaCadena")
    @Expose
    private String fechaCadena;
    @SerializedName("horaInicioCadena")
    @Expose
    private String horaInicioCadena;
    @SerializedName("horaFinCadena")
    @Expose
    private String horaFinCadena;
    @SerializedName("idEmpleado")
    @Expose
    private Paciente idEmpleado;
    @SerializedName("idCliente")
    @Expose
    private Paciente idCliente;
    @SerializedName("observacion")
    @Expose
    private String observacion;
    @SerializedName("flagAsistio")
    @Expose
    private String flagAsistio;


    public Turno() {
    }

    public Integer getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Integer idReserva) {
        this.idReserva = idReserva;
    }

    public String getFechaCadena() {
        return fechaCadena;
    }

    public void setFechaCadena(String fechaCadena) {
        this.fechaCadena = fechaCadena;
    }

    public String getHoraInicioCadena() {
        return horaInicioCadena;
    }

    public void setHoraInicioCadena(String horaInicioCadena) {
        this.horaInicioCadena = horaInicioCadena;
    }

    public String getHoraFinCadena() {
        return horaFinCadena;
    }

    public void setHoraFinCadena(String horaFinCadena) {
        this.horaFinCadena = horaFinCadena;
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

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getFlagAsistio() {
        return flagAsistio;
    }

    public void setFlagAsistio(String flagAsistio) {
        this.flagAsistio = flagAsistio;
    }

    @Override
    public String toString() {
        return "Turno{" +
                "idReserva=" + idReserva +
                ", fechaCadena='" + fechaCadena + '\'' +
                ", horaInicioCadena='" + horaInicioCadena + '\'' +
                ", horaFinCadena='" + horaFinCadena + '\'' +
                ", idEmpleado=" + idEmpleado +
                ", idCliente=" + idCliente +
                '}';
    }
}
