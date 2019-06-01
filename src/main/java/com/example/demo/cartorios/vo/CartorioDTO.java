package com.example.demo.cartorios.vo;

import com.example.demo.cartorios.model.Cartorio;
import com.example.demo.cartorios.model.Documento;

import java.util.ArrayList;
import java.util.List;

public class CartorioDTO {

    private Long id;

    private String nome;
    private String email;

    private List<Long> documentoIdList = new ArrayList<>();

    public CartorioDTO() {
    }

    public CartorioDTO(Long id, String nome, String email, List<Long> documentoIdList) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.documentoIdList = documentoIdList;
    }

    public static CartorioDTO of(Cartorio cartorio) {
        List<Long> documentoDTOList = new ArrayList<>();

        if (cartorio.getDocumentoList() != null) {
            for (Documento documento : cartorio.getDocumentoList()) {
                documentoDTOList.add(documento.getId());
            }
        }

        return new CartorioDTO(
                cartorio.getId(),
                cartorio.getNome(),
                cartorio.getEmail(),
                documentoDTOList
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

    public List<Long> getDocumentoIdList() {
        return documentoIdList;
    }

    public void setDocumentoIdList(List<Long> documentoIdList) {
        this.documentoIdList = documentoIdList;
    }

    public void atualizarDados(CartorioDTO cartorioDTO) {

        this.email = cartorioDTO.getEmail();
        this.nome = cartorioDTO.getNome();

    }

    public void adicionarDocumentos(List<Long> documentoIdList) {

        this.documentoIdList = documentoIdList;

    }
}
