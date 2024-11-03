package com.empresa.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.empresa.models.Usuario;
import java.util.List;

@Repository
public class UsuarioDAO {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Usuario findByEmail(String email) {
        String sql = "SELECT * FROM usuarios WHERE correo = ? AND estatus = 1";
        try {
            return jdbcTemplate.queryForObject(sql,
                new BeanPropertyRowMapper<>(Usuario.class),
                email);
        } catch (Exception e) {
            return null;
        }
    }

    public List<Usuario> findAll() {
        String sql = "SELECT * FROM usuarios WHERE estatus = 1";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Usuario.class));
    }

    public void save(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nombre, correo, contraseña, id_rol, estatus) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
            usuario.getNombre(),
            usuario.getCorreo(),
            usuario.getContraseña(),
            usuario.getIdRol(),
            usuario.getEstatus()
        );
    }
}
