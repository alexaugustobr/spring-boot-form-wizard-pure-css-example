package com.example.demo.cartorios.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Documento {

    @Id
    @GeneratedValue
    private Long id;

    private String nome;

    @ManyToMany(mappedBy = "documentoList")
    private List<Cartorio> cartorioList = new ArrayList<>();

    public Documento() {
    }

    public Documento(Long id, String nome, List<Cartorio> cartorioList) {
        this.id = id;
        this.nome = nome;
        this.cartorioList = cartorioList;
    }

    public Documento(String nome) {
        this.nome = nome;
    }

    public Documento(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Cartorio> getCartorioList() {
        return cartorioList;
    }

    public void setCartorioList(List<Cartorio> cartorioList) {
        this.cartorioList = cartorioList;
    }
}
