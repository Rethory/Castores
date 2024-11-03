package com.empresa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.security.core.Authentication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.empresa.services.ProductoService;
import com.empresa.services.MovimientoService;
import com.empresa.models.Producto;
import com.empresa.models.Movimiento;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.List;          
import java.util.ArrayList;


@Controller
public class InventarioController {
    
    @Autowired
    private ProductoService productoService;

    @Autowired
    private MovimientoService movimientoService;

    @GetMapping("/inventario")
    public String mostrarInventario(Model model, Authentication authentication) {
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("productos", productoService.obtenerTodosLosProductos());
        return "inventario";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/inventario/inactivos")
    public String mostrarInactivos(Model model) {
        model.addAttribute("productosInactivos", productoService.obtenerProductosInactivos());
        return "productos-inactivos";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/inventario/agregar")
    public String agregarProducto(@RequestParam String nombreProducto, 
                                @RequestParam int cantidad) {
        Producto producto = new Producto();
        producto.setNombreProducto(nombreProducto);
        producto.setCantidad(cantidad);
        producto.setEstatus(1);
        productoService.guardarProducto(producto);
        return "redirect:/inventario";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/inventario/editar/{id}")  // Cambiado para coincidir con la ruta del formulario
    public String editarProducto(@PathVariable("id") int id, 
                           @RequestParam("cantidad") int cantidad,
                           Authentication auth) {
        try {
            Producto producto = productoService.obtenerProductoPorId(id);
            int cantidadAnterior = producto.getCantidad();
            productoService.actualizarCantidad(id, cantidad);
            
            // Registrar movimiento
            Movimiento movimiento = new Movimiento();
            movimiento.setIdProducto(id);
            movimiento.setTipoMovimiento("Edición");
            movimiento.setCantidad(cantidad - cantidadAnterior);
            movimiento.setFechaHora(LocalDateTime.now());
            movimiento.setIdUsuario(1); // Obtener el ID del usuario actual
            movimientoService.registrarMovimiento(movimiento);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/inventario";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/inventario/eliminar/{id}")
    public String eliminarProducto(@PathVariable("id") int id, Authentication auth) {
        try {
            productoService.eliminarProducto(id);
            
            // Registrar movimiento
            Movimiento movimiento = new Movimiento();
            movimiento.setIdProducto(id);
            movimiento.setTipoMovimiento("Eliminación");
            movimiento.setFechaHora(LocalDateTime.now());
            movimiento.setIdUsuario(1); // Obtener el ID del usuario actual
            movimientoService.registrarMovimiento(movimiento);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/inventario";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/inventario/reactivar/{id}")  // Cambiado para coincidir con el form
    public String reactivarProducto(@PathVariable("id") int id, Authentication auth) {
    try {
        productoService.reactivarProducto(id);
        
        // Registrar movimiento
        Movimiento movimiento = new Movimiento();
        movimiento.setIdProducto(id);
        movimiento.setTipoMovimiento("Reactivación");
        movimiento.setFechaHora(LocalDateTime.now());
        movimiento.setIdUsuario(1); 
        movimientoService.registrarMovimiento(movimiento);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return "redirect:/inventario/inactivos";
}

    @GetMapping
    public String mostrarHistorico(Model model, 
                         @RequestParam(required = false) String tipoMovimiento,
                         @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaInicio,
                         @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaFin) {

    if (fechaInicio != null) {
        fechaInicio = fechaInicio.withHour(0).withMinute(0).withSecond(0);
    }
    if (fechaFin != null) {
        fechaFin = fechaFin.withHour(23).withMinute(59).withSecond(59);
    }

    List<Movimiento> movimientos = new ArrayList<>();
    try {
        if (tipoMovimiento != null || fechaInicio != null || fechaFin != null) {
            movimientos = movimientoService.buscarMovimientosFiltrados(tipoMovimiento, fechaInicio, fechaFin);
        } else {
            movimientos = movimientoService.obtenerTodosLosMovimientos();
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    
    model.addAttribute("movimientos", movimientos);
    return "movimientos";
}

}
