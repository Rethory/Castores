package com.empresa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.empresa.dao.MovimientoDAO;
import com.empresa.models.Movimiento;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MovimientoService {
    
    @Autowired
    private MovimientoDAO movimientoDAO;
    
    public List<Movimiento> obtenerTodosLosMovimientos() {
        return movimientoDAO.obtenerTodosLosMovimientos();
    }
    
    public List<Movimiento> buscarMovimientosFiltrados(String tipoMovimiento, 
                                                      LocalDateTime fechaInicio, 
                                                      LocalDateTime fechaFin) {
        return movimientoDAO.buscarMovimientosFiltrados(tipoMovimiento, fechaInicio, fechaFin);
    }
    
    public void registrarMovimiento(Movimiento movimiento) {
        movimientoDAO.guardarMovimiento(movimiento);
    }
}
