package com.empresa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;

import com.empresa.services.ProductoService;
import com.empresa.services.MovimientoService;
import com.empresa.services.UsuarioService;
import com.empresa.models.Producto;
import com.empresa.models.Movimiento;
import java.time.LocalDateTime;

@Controller
public class SalidasController {

    @Autowired
    private ProductoService productoService;
    
    @Autowired
    private MovimientoService movimientoService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/salidas")
    public String mostrarSalidas(Model model) {
        model.addAttribute("productos", productoService.obtenerTodosLosProductos());
        return "salidas";
    }

    @PutMapping("/api/productos/{id}/cantidad")
    @ResponseBody
    public ResponseEntity<?> actualizarCantidad(
            @PathVariable("id") int idProducto,
            @RequestBody CantidadRequest request,
            Authentication authentication) {
        
        try {
            Producto producto = productoService.obtenerProductoPorId(idProducto);
            if (producto == null) {
                return ResponseEntity.notFound().build();
            }

            int cantidadActual = producto.getCantidad();
            int nuevaCantidad = cantidadActual + request.getCantidad(); // request.getCantidad() será negativo para salidas

            if (nuevaCantidad < 0) {
                return ResponseEntity.badRequest()
                    .body(new ErrorResponse("No hay suficiente inventario disponible"));
            }

            // Actualizar la cantidad
            productoService.actualizarCantidad(idProducto, nuevaCantidad);

            // Obtener el ID del usuario autenticado
            User user = (User) authentication.getPrincipal();
            Integer idUsuario = usuarioService.obtenerIdUsuarioPorCorreo(user.getUsername());
            
            if (idUsuario == null) {
                return ResponseEntity.badRequest()
                    .body(new ErrorResponse("Error: No se pudo identificar al usuario"));
            }

            // Registrar el movimiento
            Movimiento movimiento = new Movimiento();
            movimiento.setIdProducto(idProducto);
            movimiento.setTipoMovimiento("Salida");
            movimiento.setCantidad(-request.getCantidad()); // Convertir a positivo para el registro
            movimiento.setFechaHora(LocalDateTime.now());
            movimiento.setIdUsuario(idUsuario);
            
            movimientoService.guardarMovimiento(movimiento);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(new ErrorResponse("Error al procesar la solicitud: " + e.getMessage()));
        }
    }
}

// Clases auxiliares
class CantidadRequest {
    private int cantidad;

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}

class ErrorResponse {
    private String error;

    public ErrorResponse(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}