package br.com.tqi.loancompany.resources.dto;

import br.com.tqi.loancompany.domain.enums.Perfil;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ClienteDto {

    private Integer id;

    @NotEmpty(message = "Preenchimento Obrigatório")
    @Length(min = 2, max = 120, message = "O nome ser entre 5 e 120 caracteres")
    private String nome;

    @NotEmpty(message = "Preenchimento Obrigatório")
    @Email(message = "Email inválido")
    private String email;

    @NotEmpty(message = "Preenchimento Obrigatório!")
    @CPF(message = "Número de CPF Inválido!")
    private String cpf;

    @Length(min = 8, max = 10, message = "O documento deve conter de 8 a 10 caracteres")
    private String rg;

    @NotNull(message = "Peenchimento Obrigatório")
    private BigDecimal renda;

    @NotEmpty(message = "Preenchimento Obrigatório")
    @Length(min = 6, max = 15, message = "A Senha deve conter entre 6 e 15 caracteres")
    private String senha;

    private Perfil idPerfil;

    private List<EnderecoDto> enderecos = new ArrayList<>();

    private List<EmprestimoDto> emprestimos = new ArrayList<>();

    public ClienteDto() {
    }

    public ClienteDto(String email, String senha) {
        this.email = email;
        this.senha = senha;

    }

    public ClienteDto(Integer id, String nome, String email, String senha, String cpf, String rg, BigDecimal renda,
                      Perfil idPerfil){
        super();
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.rg = rg;
        this.renda = renda;
        this.idPerfil = idPerfil;
    }


}
