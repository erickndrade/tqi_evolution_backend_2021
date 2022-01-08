package br.com.tqi.loancompany.resources.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class EnderecoDto{

    private Integer id;
    private String logradouro;
    @NotEmpty(message = "Peenchimento Obrigatório")
    private String numero;
    private String complemento;
    private String bairro;
    @NotEmpty(message = "Peenchimento Obrigatório")
    private String cep;
    private String cidade;
    private String estado;

    public EnderecoDto() {
    }

    public EnderecoDto(Integer id, String logradouro, String numero, String complemento, String bairro, String cep,
                       String cidade, String estado) {
        super();
        this.id = id;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cep = cep;
        this.cidade = cidade;
        this.estado = estado;
    }
}
