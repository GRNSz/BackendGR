package com.crudteste.crud.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome não pode estar em branco")
    private String nome;

    @Email(message = "O email está incorreto")
    @Column(unique = true, nullable = false)
    @NotBlank(message = "O email é obrigatório")
    private String email;

    @NotBlank(message = "A senha não pode estar vazia")
    private String senha;

    @Enumerated(EnumType.STRING)
    private Role role;

    @NotNull(message = "O telefone não pode estar vazio")
    // @Pattern(
            // regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}",
            // message = "Telefone inválido. Formato esperado: (11) 99999-9999"
    // )
    private String telefone;

    @CPF(message = "CPF Inválido")
    @Column(unique = true, nullable = false)
    private String cpf;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private boolean active = true;
}
