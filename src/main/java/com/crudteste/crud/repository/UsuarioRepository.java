package com.crudteste.crud.repository;

import com.crudteste.crud.model.Usuario;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

        // Buscando por Email
        Optional<Usuario> findByEmail(String email);

        // Busca por CPF
        Optional<Usuario> findByCpf(@CPF(message = "CPF Inválido") String cpf);

    }

