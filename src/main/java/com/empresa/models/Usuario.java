package com.empresa.models;

public class Usuario {
    private int idUsuario;
    private String nombre;
    private String correo;
    private String contraseña;
    private int idRol;
    private int estatus;

    // Getters
    public int getIdUsuario() { return idUsuario; }
    public String getNombre() { return nombre; }
    public String getCorreo() { return correo; }
    public String getContraseña() { return contraseña; }
    public int getIdRol() { return idRol; }
    public int getEstatus() { return estatus; }

    // Setters
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setCorreo(String correo) { this.correo = correo; }
    public void setContraseña(String contraseña) { this.contraseña = contraseña; }
    public void setIdRol(int idRol) { this.idRol = idRol; }
    public void setEstatus(int estatus) { this.estatus = estatus; }
}
