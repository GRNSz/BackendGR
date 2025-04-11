package com.crudteste.crud.service;

import com.crudteste.crud.model.Usuario;
import com.crudteste.crud.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

        public Usuario salvarUsuario(Usuario usuario) {
            // Criptografando a senha antes de salvar no banco
            String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
            usuario.setSenha(senhaCriptografada);

            return usuarioRepository.save(usuario);
        }

        public Optional<Usuario> buscaPorId(Long id) {
            return usuarioRepository.findById(id);
        }

        public List<Usuario> listarTodos(){
            return usuarioRepository.findAll();
        }

        public Optional<Usuario> buscaPorEmail(String email) {
            return usuarioRepository.findByEmail(email);
        }

        public Optional<Usuario> buscaPorCpf(String cpf) {
            return usuarioRepository.findByCpf(cpf);
        }

        public void deletarUsuario(Long id) {
            usuarioRepository.deleteById(id);
        }

        public Usuario atualizarUsuario(Long id, Usuario usuarioAtualizado) {
            Optional<Usuario> existente = usuarioRepository.findById(id);

            // if q valida se as informações existem antes de atualizar
            if (existente.isPresent()) {
                Usuario usuario = existente.get();
                usuario.setNome(usuarioAtualizado.getNome());
                usuario.setEmail(usuarioAtualizado.getEmail());
                usuario.setCpf(usuarioAtualizado.getCpf());
                usuario.setTelefone(usuarioAtualizado.getTelefone());
                usuario.setRole(usuarioAtualizado.getRole());
                usuario.setActive(usuarioAtualizado.isActive());

                // Código para atualização de senha
                if (usuarioAtualizado.getSenha() != null && !usuarioAtualizado.getSenha().isEmpty()) {
                    String senhaCriptografada = passwordEncoder.encode(usuarioAtualizado.getSenha());
                    usuario.setSenha(senhaCriptografada);
                }
                return usuarioRepository.save(usuario);
            } else {
                throw new RuntimeException("Usuario não encontrado com ID: " + id);
            }
        }
    }
