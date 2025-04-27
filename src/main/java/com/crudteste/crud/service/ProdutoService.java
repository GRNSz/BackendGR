package com.crudteste.crud.service;

import com.crudteste.crud.model.Produto;
import com.crudteste.crud.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto saveProd(Produto produto) {

        // Validando se o produto já foi cadastrado.
        if (produtoRepository.findBysku(produto.getSku()).isPresent()) {
            throw new IllegalArgumentException("Produto já cadastrado");
        }

        // Aqui eu valido se o nome do produto já existe.
        if (produtoRepository.findByNome(produto.getNome()).isPresent()) {
            throw new IllegalArgumentException("Nome de produto já cadastrado");
        }

        // Finaliza e salva o produto.
        return produtoRepository.save(produto);
    }

    public Optional<Produto> findBySku(Long sku) {
        return produtoRepository.findBysku(sku);
    }

    public Optional<Produto> findByNome(String nome) {
        return produtoRepository.findByNome(nome);
    }

    public List<Produto> listAll() {
        return produtoRepository.findAll();
    }

    public void deleteBySku(Long sku) {
        produtoRepository.deleteById(sku);
    }

    public Produto atualizarProduto(Long id, Produto produtoAtualizado) {
        Optional<Produto> produtoExistente = produtoRepository.findById(id);

        produtoExistente.ifPresent(produto -> produtoAtualizado.setNome(produto.getNome()));



        return produtoAtualizado;
    }

}
