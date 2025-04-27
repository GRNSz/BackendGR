package com.crudteste.crud.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@JsonIgnoreProperties
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long sku;

    @NotBlank
    private String nome;

    @NotBlank
    private String categoria;

    @NotBlank
    private String fornecedor;

    @NotNull
    private double precoCusto;

    @NotBlank
    private String descricaoProd;

    @NotNull
    private int estoqueProd;

    @CreationTimestamp
    private LocalDateTime createdAtProd;

    @UpdateTimestamp
    private LocalDateTime updatedAtProd;

    private boolean active = true;
}
