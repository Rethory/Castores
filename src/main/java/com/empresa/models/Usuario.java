package com.empresa.models;

public class Usuario {
    private int idUsuario;
    private String nombre;
    private String correo;
    private String contraseña;
    private int idRol;
    private int estatus;
    
    // Constructor vacío
    public Usuario() {
    }
    
    // Constructor completo
    public Usuario(int idUsuario, String nombre, String correo, String contraseña, int idRol, int estatus) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.correo = correo;
        this.contraseña = contraseña;
        this.idRol = idRol;
        this.estatus = estatus;
    }
    
    // Getters y Setters
    public int getIdUsuario() {
        return idUsuario;
    }
    
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getCorreo() {
        return correo;
    }
    
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    public String getContraseña() {
        return contraseña;
    }
    
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    
    public int getIdRol() {
        return idRol;
    }
    
    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }
    
    public int getEstatus() {
        return estatus;
    }
    
    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }
    
    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", idRol=" + idRol +
                ", estatus=" + estatus +
                '}';
    }
}
