package com.crudteste.crud.repository;

import com.crudteste.crud.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    Optional<Produto> findBysku(Long sku);

    Optional<Produto> findByNome(String nome);

    Optional<Produto> findByCategoria(String categoria);

}
