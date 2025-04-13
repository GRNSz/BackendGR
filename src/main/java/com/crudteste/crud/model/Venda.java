package com.crudteste.crud.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Venda extends Produto {

    @Id
    private String codigo;

}
