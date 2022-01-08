package br.com.tqi.loancompany.resources.dto;

import lombok.Getter;

@Getter
public class ValidacaoDto {

//    private String campo;
    private String erro;

    public ValidacaoDto(String erro) {
//        this.campo = campo;
        this.erro = erro;
    }
}
