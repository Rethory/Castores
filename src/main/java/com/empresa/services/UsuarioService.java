package com.empresa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.empresa.dao.UsuarioDAO;
import com.empresa.models.Usuario;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioDAO usuarioDAO;
    
    public Integer obtenerIdUsuarioPorCorreo(String correo) {
        return usuarioDAO.obtenerIdUsuarioPorCorreo(correo);
    }
}
