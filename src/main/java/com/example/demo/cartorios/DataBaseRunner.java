package com.example.demo.cartorios;


import com.example.demo.cartorios.model.Cartorio;
import com.example.demo.cartorios.model.Documento;
import com.example.demo.cartorios.repository.CartorioRepository;
import com.example.demo.cartorios.repository.DocumentoRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataBaseRunner implements ApplicationRunner {

    private DocumentoRepository documentoRepository;
    private CartorioRepository cartorioRepository;

    public DataBaseRunner(DocumentoRepository documentoRepository, CartorioRepository cartorioRepository) {
        this.documentoRepository = documentoRepository;
        this.cartorioRepository = cartorioRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (documentoRepository.findAll().isEmpty()) {

            for (int i = 1; i <= 10; i++) {
                documentoRepository.save(new Documento(
                        "Certidão - "+i
                ));
            }

            cartorioRepository.save(new Cartorio(
                    "Cartorio 1",
                    "cartorio@email.com.br",
                    documentoRepository.findAll()
            ));

        }
    }
}
