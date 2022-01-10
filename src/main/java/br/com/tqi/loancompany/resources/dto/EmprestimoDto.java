package br.com.tqi.loancompany.resources.dto;

import br.com.tqi.loancompany.domain.Cliente;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class EmprestimoDto {

    private Long id;

    @NotNull(message = "Preenchimento Obrigatório")
    private Double valorEmprestimo;

    @Min(value = 1, message = "O mínino de parcelas é 1.")
    @NotNull(message = "Preenchimento Obrigatório")
    private Integer quantidadeParcelas;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate primeiraParcela;

    private String emailCliente;
//
//    private Cliente cliente;

    private BigDecimal rendaCliente;

    public EmprestimoDto() {
    }

    public EmprestimoDto(Long id, Double valorEmprestimo, Integer quantidadeParcelas) {
        setId(id);
        setValorEmprestimo(valorEmprestimo);
        setQuantidadeParcelas(quantidadeParcelas);
    }

    public EmprestimoDto(Long id, Double valorEmprestimo, Integer quantidadeParcelas, LocalDate primeiraParcela) {
        super();
        this.id = id;
        this.valorEmprestimo = valorEmprestimo;
        this.quantidadeParcelas = quantidadeParcelas;
        this.primeiraParcela = primeiraParcela;

    }

}
