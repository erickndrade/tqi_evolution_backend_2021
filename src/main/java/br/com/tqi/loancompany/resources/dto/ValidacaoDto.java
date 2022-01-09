package br.com.tqi.loancompany.resources.dto;

import lombok.Getter;

@Getter
public class ValidacaoDto {

    private String erro;

    public ValidacaoDto(String erro) {
        this.erro = erro;
    }
}
