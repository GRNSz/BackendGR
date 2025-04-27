package com.crudteste.crud.repository;

import com.crudteste.crud.model.Fornecedor;
import org.hibernate.validator.constraints.br.CNPJ;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {

    // Buscando por CNPJ
    Optional<Fornecedor> findByCnpj(@CNPJ(message = "CNPJ Inválido") String cnpj);

    // Buscando por nome
    Optional<Fornecedor> findByNome(String nome);

}
