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
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioDAO usuarioDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioDAO.findByEmail(username);
        
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        
        if (usuario.getIdRol() == 1) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else if (usuario.getIdRol() == 2) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ALMACENISTA"));
        }

        return new User(usuario.getCorreo(), 
                       usuario.getContraseña(), 
                       true, 
                       true, 
                       true, 
                       true, 
                       authorities);
    }
}
