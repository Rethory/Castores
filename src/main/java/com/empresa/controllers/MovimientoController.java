package com.empresa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.empresa.services.MovimientoService;
import com.empresa.models.Movimiento;
import org.springframework.security.access.prepost.PreAuthorize;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

@Controller
@RequestMapping("/movimientos")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class MovimientoController {
    
    @Autowired
    private MovimientoService movimientoService;

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

    List<Movimiento> movimientos;
    try {
        if (tipoMovimiento != null || fechaInicio != null || fechaFin != null) {
            movimientos = movimientoService.buscarMovimientosFiltrados(tipoMovimiento, fechaInicio, fechaFin);
        } else {
            movimientos = movimientoService.obtenerTodosLosMovimientos();
        }
    } catch (Exception e) {
        movimientos = new ArrayList<>();
        e.printStackTrace();
    }
    
    model.addAttribute("movimientos", movimientos);
    return "movimientos";
}

}
