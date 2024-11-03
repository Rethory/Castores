package com.empresa.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.empresa.models.Movimiento;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;

@Repository
public class MovimientoDAO {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public void guardarMovimiento(Movimiento movimiento) {
        String sql = "INSERT INTO movimientos (tipoMovimiento, cantidad, fechaHora, idProducto, idUsuario) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
            movimiento.getTipoMovimiento(),
            movimiento.getCantidad(),
            movimiento.getFechaHora(),
            movimiento.getIdProducto(),
            movimiento.getIdUsuario()
        );
    }
    
    public List<Movimiento> obtenerTodosLosMovimientos() {
    String sql = "SELECT m.*, p.nombreProducto, u.nombre as nombreUsuario " +  // Usamos un alias
                "FROM movimientos m " +
                "JOIN productos p ON m.idProducto = p.idProducto " +
                "JOIN usuarios u ON m.idUsuario = u.idUsuario " +
                "ORDER BY m.fechaHora DESC";
    try {
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Movimiento movimiento = new Movimiento();
            movimiento.setIdMovimiento(rs.getInt("idMovimiento"));
            movimiento.setIdProducto(rs.getInt("idProducto"));
            movimiento.setTipoMovimiento(rs.getString("tipoMovimiento"));
            movimiento.setCantidad(rs.getInt("cantidad"));
            
            LocalDateTime fechaHora = rs.getTimestamp("fechaHora").toLocalDateTime();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            movimiento.setFechaHoraFormateada(fechaHora.format(formatter));
            movimiento.setFechaHora(fechaHora);
            
            movimiento.setIdUsuario(rs.getInt("idUsuario"));
            movimiento.setNombreProducto(rs.getString("nombreProducto"));
            movimiento.setNombreUsuario(rs.getString("nombreUsuario"));
            return movimiento;
        });
    } catch (Exception e) {
        e.printStackTrace();
        return new ArrayList<>();
    }
}

public List<Movimiento> buscarMovimientosFiltrados(String tipoMovimiento, 
                                              LocalDateTime fechaInicio, 
                                              LocalDateTime fechaFin) {
    StringBuilder sql = new StringBuilder(
        "SELECT m.*, p.nombreProducto, u.nombre as nombreUsuario " +  // Usamos un alias
        "FROM movimientos m " +
        "JOIN productos p ON m.idProducto = p.idProducto " +
        "JOIN usuarios u ON m.idUsuario = u.idUsuario " +
        "WHERE 1=1");
    
    List<Object> params = new ArrayList<>();
    
    if (tipoMovimiento != null && !tipoMovimiento.isEmpty()) {
        sql.append(" AND m.tipoMovimiento = ?");
        params.add(tipoMovimiento);
    }
    
    if (fechaInicio != null) {
        sql.append(" AND m.fechaHora >= ?");
        params.add(fechaInicio);
    }
    
    if (fechaFin != null) {
        sql.append(" AND m.fechaHora <= ?");
        params.add(fechaFin);
    }
    
    sql.append(" ORDER BY m.fechaHora DESC");
    
    try {
        return jdbcTemplate.query(sql.toString(), 
            params.toArray(),
            (rs, rowNum) -> {
                Movimiento movimiento = new Movimiento();
                movimiento.setIdMovimiento(rs.getInt("idMovimiento"));
                movimiento.setIdProducto(rs.getInt("idProducto"));
                movimiento.setTipoMovimiento(rs.getString("tipoMovimiento"));
                movimiento.setCantidad(rs.getInt("cantidad"));
                movimiento.setFechaHora(rs.getTimestamp("fechaHora").toLocalDateTime());
                movimiento.setIdUsuario(rs.getInt("idUsuario"));
                movimiento.setNombreProducto(rs.getString("nombreProducto"));
                movimiento.setNombreUsuario(rs.getString("nombreUsuario")); // Usamos el alias
                return movimiento;
            });
    } catch (Exception e) {
        e.printStackTrace();
        return new ArrayList<>();
    }
}
}