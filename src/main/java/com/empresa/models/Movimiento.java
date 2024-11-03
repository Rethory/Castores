package com.empresa.models;

import java.time.LocalDateTime;

public class Movimiento {
    private int idMovimiento;
    private String tipoMovimiento;
    private int cantidad;
    private LocalDateTime fechaHora;
    private int idProducto;
    private int idUsuario;
    private String nombreProducto; // Para mostrar en la vista
    private String nombreUsuario; // Para mostrar en la vista
    private String fechaHoraFormateada;

    // Getters
    public int getIdMovimiento() { return idMovimiento; }
    public String getTipoMovimiento() { return tipoMovimiento; }
    public int getCantidad() { return cantidad; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public int getIdProducto() { return idProducto; }
    public int getIdUsuario() { return idUsuario; }
    public String getNombreProducto() { return nombreProducto; }
    public String getNombreUsuario() { return nombreUsuario; }
    public String getFechaHoraFormateada() { return fechaHoraFormateada; }

    // Setters
    public void setIdMovimiento(int idMovimiento) { this.idMovimiento = idMovimiento; }
    public void setTipoMovimiento(String tipoMovimiento) { this.tipoMovimiento = tipoMovimiento; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }
    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }
    public void setNombreProducto(String nombreProducto) { this.nombreProducto = nombreProducto; }
    public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; }
    public void setFechaHoraFormateada(String fechaHoraFormateada) { this.fechaHoraFormateada = fechaHoraFormateada; }
}
