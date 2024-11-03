package com.empresa.models;

import java.time.LocalDateTime;

public class Producto {
    private int idProducto;
    private String nombreProducto;
    private int cantidad;
    private int estatus;
    private LocalDateTime fechaCreacion;
    private Integer idUsuario;

    // Getters
    public int getIdProducto() { return idProducto; }
    public String getNombreProducto() { return nombreProducto; }
    public int getCantidad() { return cantidad; }
    public int getEstatus() { return estatus; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public Integer getIdUsuario() { return idUsuario; }

    // Setters
    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }
    public void setNombreProducto(String nombreProducto) { this.nombreProducto = nombreProducto; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    public void setEstatus(int estatus) { this.estatus = estatus; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }
    public void setIdUsuario(Integer idUsuario) { this.idUsuario = idUsuario; }
}
