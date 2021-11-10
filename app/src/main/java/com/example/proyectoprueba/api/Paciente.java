package com.example.proyectoprueba.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Paciente {
    @SerializedName("idPersona")
    @Expose
    private Integer idPersona;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("apellido")
    @Expose
    private String apellido;
    @SerializedName("telefono")
    @Expose
    private String telefono;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("ruc")
    @Expose
    private String ruc;
    @SerializedName("cedula")
    @Expose
    private String cedula;
    @SerializedName("fechaNacimiento")
    @Expose
    private String fechaNacimiento;
    @SerializedName("tipoPersona")
    @Expose
    private String tipoPersona;
    @SerializedName("usuarioLogin")
    @Expose
    private String usuarioLogin;

    public Paciente() {
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public String getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(String usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "idPersona=" + idPersona +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                ", ruc='" + ruc + '\'' +
                ", cedula='" + cedula + '\'' +
                ", fechaNacimiento='" + fechaNacimiento + '\'' +
                ", tipoPersona='" + tipoPersona + '\'' +
                ", usuarioLogin='" + usuarioLogin + '\'' +
                '}';
    }
}

