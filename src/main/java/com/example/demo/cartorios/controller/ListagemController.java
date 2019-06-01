package com.example.demo.cartorios.controller;

import com.example.demo.cartorios.service.CartorioService;
import com.example.demo.cartorios.vo.CartorioDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class ListagemController {

    private CartorioService cartorioService;

    public ListagemController(CartorioService cartorioService) {
        this.cartorioService = cartorioService;
    }

    @GetMapping
    public String home (Model model) {

        return "redirect:/listagem";
    }

    @GetMapping("/listagem")
    public String listagem (Model model) {

        List<CartorioDTO> cartorioDTOList = cartorioService.buscarTodos();

        model.addAttribute("cartorioDTOList", cartorioDTOList);

        return "listagem";
    }

}
