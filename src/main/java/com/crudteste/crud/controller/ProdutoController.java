package com.crudteste.crud.controller;

import com.crudteste.crud.model.Produto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    public ResponseEntity<Produto> salvarProduto(@RequestBody @Valid Produto produto) {
        return ResponseEntity.ok(produto);
    };

}
