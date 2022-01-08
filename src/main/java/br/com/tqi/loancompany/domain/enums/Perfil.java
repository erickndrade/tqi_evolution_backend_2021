package br.com.tqi.loancompany.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Perfil {

    ADMIN(1, "ADMIN"),
    CLIENTE(2, "CLIENTE");

    private Integer codigo;
    private String descricao;

    private Perfil(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao () {
        return descricao;
    }

    @JsonCreator
    public static Perfil toEnum(Integer valor) {

        if (valor == null) {
            throw new IllegalArgumentException("O parametro valor não foi informado!");
        }
            for (Perfil perfil : Perfil.values()){
                if (valor.equals(perfil.getCodigo())){
                    return perfil;
                }
        }
            throw new IllegalArgumentException("Id Inválido: " + valor);
    }
}
