package com.example.demo.cartorios.session;

import com.example.demo.cartorios.vo.CartorioDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class CartorioCadastroSession {

    private boolean edicao = false;

    private CartorioDTO cartorioDTO;

    public boolean isEdicao() {
        return edicao;
    }

    public void setEdicao(boolean edicao) {
        this.edicao = edicao;
    }

    public CartorioDTO getCartorioDTO() {
        return cartorioDTO;
    }

    public void setCartorioDTO(CartorioDTO cartorioDTO) {
        this.cartorioDTO = cartorioDTO;
    }

    public void iniciarNovaSessao() {
        this.cartorioDTO = new CartorioDTO();
        this.edicao = false;
    }

    public void iniciarNovaSessao(CartorioDTO cartorioDTO) {
        this.cartorioDTO = cartorioDTO;
        this.edicao = true;
    }

    public boolean isSessaoInicializada() {
        return cartorioDTO != null;
    }

    public void limparSessao() {
        this.cartorioDTO = null;
        this.edicao = false;
    }
}
