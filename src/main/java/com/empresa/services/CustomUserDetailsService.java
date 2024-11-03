package com.empresa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.empresa.dao.UsuarioDAO;
import com.empresa.models.Usuario;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioDAO usuarioDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioDAO.obtenerUsuarioPorCorreo(username);
        
         if (usuario == null) {
        System.out.println("Usuario no encontrado: " + username);
        throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }
    
        if (usuario.getContraseña() == null || usuario.getContraseña().isEmpty()) {
        System.out.println("Contraseña vacía para usuario: " + username);
        throw new UsernameNotFoundException("Credenciales inválidas para: " + username);
        }


        String rol = usuario.getIdRol() == 1 ? "ROLE_ADMIN" : "ROLE_ALMACENISTA";
        System.out.println("Usuario encontrado: " + username + " con rol: " + rol);

        return User.builder()
                .username(usuario.getCorreo())
                .password(usuario.getContraseña())
                .authorities(Collections.singleton(new SimpleGrantedAuthority(rol)))
                .build();
    }
}
