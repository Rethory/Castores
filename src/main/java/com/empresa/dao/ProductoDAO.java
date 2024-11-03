package com.empresa.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.empresa.models.Producto;
import java.util.List;
import java.util.ArrayList;

@Repository
public class ProductoDAO {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public void guardarProducto(Producto producto) {
        String sql = "INSERT INTO productos (nombreProducto, cantidad, estatus) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, 
            producto.getNombreProducto(), 
            producto.getCantidad(),
            producto.getEstatus()
        );
    }
    
    public List<Producto> obtenerTodosLosProductos() {
        String sql = "SELECT * FROM productos WHERE estatus = 1";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Producto.class));
    }
    
    public List<Producto> obtenerProductosInactivos() {
    try {
        String sql = "SELECT * FROM productos WHERE estatus = 0";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Producto.class));
    } catch (Exception e) {
        e.printStackTrace();
        return new ArrayList<>();
    }
}
    
    public Producto obtenerProductoPorId(int idProducto) {
        String sql = "SELECT * FROM productos WHERE idProducto = ? AND estatus = 1";
        try {
            return jdbcTemplate.queryForObject(sql, 
                new BeanPropertyRowMapper<>(Producto.class), 
                idProducto);
        } catch (Exception e) {
            return null;
        }
    }
    
    public void actualizarCantidad(int idProducto, int cantidad) {
        String sql = "UPDATE productos SET cantidad = ? WHERE idProducto = ?";
        jdbcTemplate.update(sql, cantidad, idProducto);
    }
    
    public void eliminarProducto(int idProducto) {
        String sql = "UPDATE productos SET estatus = 0 WHERE idProducto = ?";
        jdbcTemplate.update(sql, idProducto);
    }
    
    public void reactivarProducto(int idProducto) {
        String sql = "UPDATE productos SET estatus = 1 WHERE idProducto = ?";
        jdbcTemplate.update(sql, idProducto);
    }
}
