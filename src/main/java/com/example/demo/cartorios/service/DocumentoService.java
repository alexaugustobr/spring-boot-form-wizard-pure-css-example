package com.example.demo.cartorios.service;

import com.example.demo.cartorios.model.Documento;
import com.example.demo.cartorios.repository.DocumentoRepository;
import com.example.demo.cartorios.vo.DocumentoDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DocumentoService {

    private DocumentoRepository documentoRepository;

    public DocumentoService(DocumentoRepository documentoRepository) {
        this.documentoRepository = documentoRepository;
    }

    public List<DocumentoDTO> buscarTodos() {

        List<DocumentoDTO> documentoDTOList = new ArrayList<>();

        for (Documento documento : documentoRepository.findAll()) {
            documentoDTOList.add(DocumentoDTO.of(documento));
        }

        return documentoDTOList;
    }
}
