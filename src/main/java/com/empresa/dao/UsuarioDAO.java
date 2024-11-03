package com.empresa.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import com.empresa.models.Usuario;

// Importaciones SQL necesarias
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;

// Otras utilidades
import java.util.List;
import java.util.ArrayList;
import javax.sql.DataSource;

@Repository
public class UsuarioDAO {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Usuario> usuarioRowMapper = new RowMapper<Usuario>() {
        @Override
        public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(rs.getInt("idUsuario"));
            usuario.setNombre(rs.getString("nombre"));
            usuario.setCorreo(rs.getString("correo"));
            usuario.setContraseña(rs.getString("contraseña"));
            usuario.setIdRol(rs.getInt("idRol"));
            usuario.setEstatus(rs.getInt("estatus"));
            return usuario;
        }
    };

    public Usuario obtenerUsuarioPorCorreo(String correo) {
        try {
            String sql = "SELECT * FROM usuarios WHERE correo = ? AND estatus = 1";
            System.out.println("Buscando usuario con correo: " + correo); // Log para debug
            Usuario usuario = jdbcTemplate.queryForObject(sql, usuarioRowMapper, correo);
            System.out.println("Usuario encontrado: " + (usuario != null ? usuario.getCorreo() : "null")); // Log para debug
            return usuario;
        } catch (Exception e) {
            System.out.println("Error al buscar usuario: " + e.getMessage()); // Log para debug
            e.printStackTrace();
            return null;
        }
    }

    public Integer obtenerIdUsuarioPorCorreo(String correo) {
        try {
            String sql = "SELECT idUsuario FROM usuarios WHERE correo = ?";
            return jdbcTemplate.queryForObject(sql, Integer.class, correo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void guardarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nombre, correo, contraseña, idRol, estatus) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
            usuario.getNombre(),
            usuario.getCorreo(),
            usuario.getContraseña(),
            usuario.getIdRol(),
            usuario.getEstatus()
        );
    }

    public boolean validarCredenciales(String correo, String contrasena) {
        try {
            String sql = "SELECT COUNT(*) FROM usuarios WHERE correo = ? AND contraseña = ? AND estatus = 1";
            int count = jdbcTemplate.queryForObject(sql, Integer.class, correo, contrasena);
            System.out.println("Validando credenciales para: " + correo + ", resultado: " + count); // Log para debug
            return count > 0;
        } catch (Exception e) {
            System.out.println("Error al validar credenciales: " + e.getMessage()); // Log para debug
            e.printStackTrace();
            return false;
        }
    }
}