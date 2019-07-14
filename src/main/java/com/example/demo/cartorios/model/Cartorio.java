package com.example.demo.cartorios.model;

import com.example.demo.cartorios.vo.CartorioDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cartorio {

    @Id
    @GeneratedValue
    private Long id;

    private String nome;
    private String email;

    @ManyToMany
    @JoinTable(name = "CARTORIO_DOCUMENTO")
    private List<Documento> documentoList;

    public Cartorio() {
    }
    public Cartorio(Long id, String nome, String email, List<Documento> documentoList) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.documentoList = documentoList;
    }

    public Cartorio(String nome, String email, List<Documento> documentoList) {
        this.nome = nome;
        this.email = email;
        this.documentoList = documentoList;
    }

    public static Cartorio of(CartorioDTO cartorioDTO) {
        List<Documento> documentoList = new ArrayList<>();

        for (Long id : cartorioDTO.getDocumentoIdList()) {

            documentoList.add(new Documento(id));

        }

        return new Cartorio(
                cartorioDTO.getNome(),
                cartorioDTO.getEmail(),
                documentoList
        );

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Documento> getDocumentoList() {
        return documentoList;
    }

    public void setDocumentoList(List<Documento> documentoList) {
        this.documentoList = documentoList;
    }

    public Cartorio atualizarDados(CartorioDTO cartorioDTO) {
        this.nome = cartorioDTO.getNome();
        this.email = cartorioDTO.getEmail();
        return this;
    }

    public Cartorio removerTodosDocumentos() {
        this.documentoList = new ArrayList<>();
        return this;
    }

    public Cartorio adicionarDocumentos(List<Long> documentoIdList) {

        if (documentoIdList == null) {
            return this;
        }

        for (Long id : documentoIdList) {
            documentoList.add(new Documento(id));
        }

        return this;
    }
}
