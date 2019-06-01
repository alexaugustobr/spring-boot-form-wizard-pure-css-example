package com.example.demo.cartorios.vo;

import com.example.demo.cartorios.model.Documento;

public class DocumentoDTO {

    private Long id;

    private String nome;

    public DocumentoDTO(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public static DocumentoDTO of(Documento documento) {
        return new DocumentoDTO(
                documento.getId(),
                documento.getNome()
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
}
