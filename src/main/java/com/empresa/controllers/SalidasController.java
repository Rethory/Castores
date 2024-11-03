package com.empresa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.Authentication;
import com.empresa.services.ProductoService;
import com.empresa.services.MovimientoService;
import com.empresa.models.Producto;
import com.empresa.models.Usuario;

@Controller
public class SalidasController {

    @Autowired
    private ProductoService productoService;
    
    @Autowired
    private MovimientoService movimientoService;

    @GetMapping("/salidas")
    public String mostrarSalidas(Model model) {
        model.addAttribute("productos", productoService.obtenerTodosLosProductos());
        return "salidas";
    }

    @PostMapping("/salidas/registrar")
    public String registrarSalida(@RequestParam("idProducto") int idProducto,
                                 @RequestParam("cantidad") int cantidad,
                                 Authentication authentication) {
        // Obtener el producto
        Producto producto = productoService.obtenerProductoPorId(idProducto);
        
        // Verificar si hay suficiente stock
        if (producto.getCantidad() < cantidad) {
            return "redirect:/salidas?error=stock";
        }

        // Actualizar el stock
        productoService.actualizarCantidad(idProducto, producto.getCantidad() - cantidad);
        
        return "redirect:/salidas?success=true";
    }
}
