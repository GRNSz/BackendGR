package com.crudteste.crud.service;

import com.crudteste.crud.model.Usuario;
import com.crudteste.crud.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

        public Usuario salvarUsuario(Usuario usuario) {
            // Criptografando a senha antes de salvar no banco
            return usuarioRepository.save(usuario);
        }

}
