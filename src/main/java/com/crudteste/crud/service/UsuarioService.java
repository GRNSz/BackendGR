package com.crudteste.crud.service;

import com.crudteste.crud.model.Usuario;
import com.crudteste.crud.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Usuario salvarUsuario(Usuario usuario) {
        // Validação explícita da senha antes de criptografá-la
        validarSenha(usuario.getSenha());

        // Criptografando a senha antes de salvar no banco
        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);

        System.out.println("Usuario salvo com sucesso: " + usuario.getNome());
        System.out.println("Senha recebida: " + usuario.getSenha());
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> buscaPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public List<Usuario> listarTodos() {
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

        // Verifica se o usuário existe antes de atualizar
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
                // Validação explícita da senha antes de criptografá-la
                validarSenha(usuarioAtualizado.getSenha());
                String senhaCriptografada = passwordEncoder.encode(usuarioAtualizado.getSenha());
                usuario.setSenha(senhaCriptografada);
            }

            return usuarioRepository.save(usuario);
        } else {
            throw new RuntimeException("Usuário não encontrado com ID: " + id);
        }
    }

    // Metodo de validação de senha
    private void validarSenha(String senha) {
        if (!senha.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9]).{8,16}$")) {
            throw new IllegalArgumentException("A senha deve ter pelo menos 8 caracteres, uma letra maiúscula e uma minúscula, e um caractere especial!");
        }
    }

    // Metodo de validação de telefone
    private void validarTelefone(String telefone) {
        if (!telefone.matches("\\d{3}-\\d{3}-\\d{4}")) {
            throw new IllegalArgumentException("O telefone deve estar no padrão: (11) 99999-9999");
        }
    }

}
