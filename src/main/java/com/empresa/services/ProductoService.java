package com.empresa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.empresa.dao.ProductoDAO;
import com.empresa.models.Producto;
import java.util.List;

@Service
public class ProductoService {
    
    @Autowired
    private ProductoDAO productoDAO;
    
    public List<Producto> obtenerTodosLosProductos() {
        return productoDAO.obtenerTodosLosProductos();
    }
    
    public List<Producto> obtenerProductosInactivos() {
        return productoDAO.obtenerProductosInactivos();
    }
    
    public void guardarProducto(Producto producto) {
        productoDAO.guardarProducto(producto);
    }
    
    public void actualizarCantidad(int idProducto, int cantidad) {
        productoDAO.actualizarCantidad(idProducto, cantidad);
    }
    
    public void eliminarProducto(int idProducto) {
        productoDAO.eliminarProducto(idProducto);
    }
    
    public void reactivarProducto(int idProducto) {
        productoDAO.reactivarProducto(idProducto);
    }
    
    public Producto obtenerProductoPorId(int idProducto) {
        return productoDAO.obtenerProductoPorId(idProducto);
    }
}
