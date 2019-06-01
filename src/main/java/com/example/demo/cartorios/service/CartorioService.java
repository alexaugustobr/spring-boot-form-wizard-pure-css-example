package com.example.demo.cartorios.service;

import com.example.demo.cartorios.model.Cartorio;
import com.example.demo.cartorios.repository.CartorioRepository;
import com.example.demo.cartorios.vo.CartorioDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartorioService {

    private CartorioRepository cartorioRepository;

    public CartorioService(CartorioRepository cartorioRepository) {
        this.cartorioRepository = cartorioRepository;
    }

    public CartorioDTO buscarUmPorId(Long id) {

        //TODO Recomendo usar query nativa JDBC para criar diretamente um DTO

        Cartorio cartorio = cartorioRepository.findById(id).orElseThrow(() -> new RuntimeException());

        return CartorioDTO.of(cartorio);
    }

    public Long cadastrarNovo(CartorioDTO cartorioDTO) {

        Cartorio cartorio = Cartorio.of(cartorioDTO);

        return cartorioRepository.save(cartorio).getId();

    }

    public List<CartorioDTO> buscarTodos() {

        //TODO Recomendo usar query nativa JDBC para criar diretamente um DTO

        List<Cartorio> cartorioList = cartorioRepository.findAll();

        List<CartorioDTO> cartorioDTOList = new ArrayList<>();

        for (Cartorio cartorio : cartorioList) {
            cartorioDTOList.add(CartorioDTO.of(cartorio));
        }

        return cartorioDTOList;

    }

    public Long editar(Long id, CartorioDTO cartorioDTO) {

        Cartorio cartorio = cartorioRepository.findById(id).orElseThrow(() -> new RuntimeException());

        cartorio.atualizarDados(cartorioDTO)
                .removerTodosDocumentos()
                .adicionarDocumentos(cartorioDTO.getDocumentoIdList());

        cartorioRepository.save(cartorio);

        return cartorio.getId();
    }
}
