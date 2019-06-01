package com.example.demo.cartorios.controller;

import com.example.demo.cartorios.service.CartorioService;
import com.example.demo.cartorios.service.DocumentoService;
import com.example.demo.cartorios.session.CartorioCadastroSession;
import com.example.demo.cartorios.vo.CartorioDTO;
import com.example.demo.cartorios.vo.DocumentoDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.GeneratedValue;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CartorioCadastroController {

    private CartorioCadastroSession cartorioCadastroSession;
    private CartorioService cartorioService;
    private DocumentoService documentoService;

    public CartorioCadastroController(CartorioCadastroSession cartorioCadastroSession, CartorioService cartorioService, DocumentoService documentoService) {
        this.cartorioCadastroSession = cartorioCadastroSession;
        this.cartorioService = cartorioService;
        this.documentoService = documentoService;
    }

    @GetMapping("/novo")
    public String novo (Model model) {

        cartorioCadastroSession.iniciarNovaSessao();

        return "redirect:/novo/descricao";
    }

    @GetMapping("/novo/descricao")
    public String novoDescricao (Model model) {

        if (!cartorioCadastroSession.isSessaoInicializada()) {
            return "redirect:/novo";
        }

        model.addAllAttributes(popularFormStep1(cartorioCadastroSession.getCartorioDTO(), false));

        return "formstep1";
    }

    @PostMapping("/novo/descricao")
    public String novoDescricao (Model model, @Valid CartorioDTO cartorioDTO, Errors validation) {

        if (!cartorioCadastroSession.isSessaoInicializada()) {
            return "redirect:/novo";
        }

        if (validation.hasErrors()) {
            model.addAllAttributes(popularFormStep1(cartorioDTO, false));
            return "formstep1";
        }

        cartorioCadastroSession.getCartorioDTO().atualizarDados(cartorioDTO);

        return "redirect:/novo/documentos";
    }

    @GetMapping("/novo/documentos")
    public String novoDocumentos (Model model) {

        if (!cartorioCadastroSession.isSessaoInicializada()) {
            return "redirect:/novo";
        }

        List<DocumentoDTO> documentoDTOList = documentoService.buscarTodos();

        CartorioDTO cartorioDTO = cartorioCadastroSession.getCartorioDTO();

        model.addAllAttributes(popularFormStep2(cartorioDTO, documentoDTOList,false));

        return "formstep2";
    }

    @PostMapping("/novo/documentos")
    public String novoDocumentos (Model model, @RequestParam(value = "documentoIdList", required = false) ArrayList<Long> documentoIdList) {

        if (documentoIdList == null) {
            cartorioCadastroSession.getCartorioDTO().adicionarDocumentos(new ArrayList<>());
        }

        cartorioCadastroSession.getCartorioDTO().adicionarDocumentos(documentoIdList);

        return "redirect:/novo/resumo";
    }

    @GetMapping("/novo/resumo")
    public String novoResumo (Model model) {

        if (!cartorioCadastroSession.isSessaoInicializada()) {
            return "redirect:/novo";
        }

        model.addAllAttributes(popularFormStep3(cartorioCadastroSession.getCartorioDTO(), false));

        return "formstep3";
    }

    @PostMapping("/novo/resumo")
    public String novoResumoFinalizar (Model model) {

        cartorioService.cadastrarNovo(cartorioCadastroSession.getCartorioDTO());

        cartorioCadastroSession.limparSessao();

        return "redirect:/listagem";
    }



    @GetMapping("/editar/{id}")
    public String editar (Model model, @PathVariable("id") Long id) {

        CartorioDTO cartorioDTO = cartorioService.buscarUmPorId(id);

        cartorioCadastroSession.iniciarNovaSessao(cartorioDTO);

        return "redirect:/editar/"+id+"/descricao";
    }

    @GetMapping("/editar/{id}/descricao")
    public String editarDescricao (Model model, @PathVariable("id") Long id) {

        if (!cartorioCadastroSession.isSessaoInicializada()) {
            return "redirect:/editar/"+id+"";
        }

        model.addAllAttributes(popularFormStep1(cartorioCadastroSession.getCartorioDTO(), true));

        return "formstep1";
    }

    @PostMapping("/editar/{id}/descricao")
    public String editarDescricao (Model model, @PathVariable("id") Long id, @Valid CartorioDTO cartorioDTO, Errors validation) {

        if (!cartorioCadastroSession.isSessaoInicializada()) {
            return "redirect:/editar/"+id+"/descricao";
        }

        if (validation.hasErrors()) {
            model.addAllAttributes(popularFormStep1(cartorioDTO, true));
            return "formstep1";
        }

        cartorioCadastroSession.getCartorioDTO().atualizarDados(cartorioDTO);

        return "redirect:/editar/"+id+"/documentos";
    }


    @GetMapping("/editar/{id}/documentos")
    public String editarDocumentos (Model model, @PathVariable("id") Long id) {

        if (!cartorioCadastroSession.isSessaoInicializada()) {
            return "redirect:/editar/"+id+"/descricao";
        }

        List<DocumentoDTO> documentoDTOList = documentoService.buscarTodos();

        CartorioDTO cartorioDTO = cartorioCadastroSession.getCartorioDTO();

        model.addAllAttributes(popularFormStep2(cartorioDTO, documentoDTOList,true));

        return "formstep2";

    }

    @PostMapping("/editar/{id}/documentos")
    public String novoDocumentos (Model model, @PathVariable("id") Long id, @RequestParam(value = "documentoIdList", required = false) ArrayList<Long> documentoIdList) {

        if (documentoIdList == null) {
            cartorioCadastroSession.getCartorioDTO().adicionarDocumentos(new ArrayList<>());
        }

        cartorioCadastroSession.getCartorioDTO().adicionarDocumentos(documentoIdList);

        return "redirect:/editar/"+id+"/resumo";
    }

    @GetMapping("/editar/{id}/resumo")
    public String editarResumo (Model model, @PathVariable("id") Long id) {

        if (!cartorioCadastroSession.isSessaoInicializada()) {
            return "redirect:/editar/"+id;
        }

        model.addAllAttributes(popularFormStep3(cartorioCadastroSession.getCartorioDTO(), true));

        return "formstep3";
    }

    @PostMapping("/editar/{id}/resumo")
    public String editarResumoFinalizar (Model model, @PathVariable("id") Long id) {

        cartorioService.editar(id, cartorioCadastroSession.getCartorioDTO());

        cartorioCadastroSession.limparSessao();

        return "redirect:/listagem";
    }

    private Map<String, Object> popularFormStep1(CartorioDTO cartorioDTO, boolean isEdicao) {
        Map<String, Object> parametros = new LinkedHashMap<>();

        parametros.put("cartorioDTO", cartorioDTO);

        String urlFormStep1;

        if (isEdicao) {
            urlFormStep1 = "/editar/" + cartorioDTO.getId() + "/descricao";
        } else {
            urlFormStep1 = "/novo/descricao";
        }

        parametros.put("urlFormStep1", urlFormStep1);

        return parametros;
    }

    private Map<String, Object> popularFormStep2(CartorioDTO cartorioDTO, List<DocumentoDTO> documentoDTOList,  boolean isEdicao) {

        Map<String, Object> parametros = new LinkedHashMap<>();

        parametros.put("documentoDTOList", documentoDTOList);
        parametros.put("documentoSelecionadoIdList", cartorioDTO.getDocumentoIdList());

        String urlFormStep1;
        String urlFormStep2;

        if (isEdicao) {
            urlFormStep1 = "/editar/" + cartorioDTO.getId() + "/descricao";
            urlFormStep2 = "/editar/" + cartorioDTO.getId() + "/documentos";
        } else {
            urlFormStep1 = "/novo/descricao";
            urlFormStep2 = "/novo/documentos";
        }

        parametros.put("urlFormStep1", urlFormStep1);
        parametros.put("urlFormStep2", urlFormStep2);

        return parametros;

    }

    private Map<String, Object> popularFormStep3(CartorioDTO cartorioDTO, boolean isEdicao) {

        Map<String, Object> parametros = new LinkedHashMap<>();

        parametros.put("cartorioDTO", cartorioDTO);

        String urlFormStep3;
        String urlFormStep2;

        if (isEdicao) {
            urlFormStep2 = "/editar/" + cartorioDTO.getId() + "/documentos";
            urlFormStep3 = "/editar/" + cartorioDTO.getId() + "/resumo";
        } else {
            urlFormStep2 = "/novo/documentos";
            urlFormStep3 = "/novo/resumo";
        }

        parametros.put("urlFormStep3", urlFormStep3);
        parametros.put("urlFormStep2", urlFormStep2);

        return parametros;

    }

}
