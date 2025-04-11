package com.crudteste.crud.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Usuario {

    // @NotBlank (message = "O id não pode estar vazio")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank (message = "O nome não pode estar em branco")
    private String nome;

    @Email (message = "O email está incorreto")
    @Column (unique = true, nullable = false)
    @NotBlank (message = "O email é obrigatório")
    private String email;

    @NotBlank (message = "A senha não pode estar vazia")
    // @Size (min = 8, message = "A senha deve ter no minimo 8 caracteres")
    // @Size (max = 15, message = "A senha deve ter no maximo 15 caracteres")
    @Pattern (
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9]).{8,16}$",
            message = "A senha deve ter pelo menos 8 caracteres, uma letra maiuscula e uma minuscula"
    )
    private String senha;

    @Enumerated (EnumType.STRING)
    private Role role;

    @NotNull (message = "O telefone não pode estar vazio")
    // @Column (unique = true)
    @Pattern(
            regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}",
            message = "Telefone inválido. Formato esperado: (11) 99999-9999"
    )
    private String telefone;

    @CPF (message = "CPF Inválido")
    @Column (unique = true, nullable = false)
    private String cpf;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private boolean active = true;

}
