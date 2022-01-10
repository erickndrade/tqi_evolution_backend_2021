package br.com.tqi.loancompany.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "EMPRESTIMO")
public class Emprestimo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double valorEmprestimo;

    private Integer quantidadeParcelas;

    private LocalDate primeiraParcela;

    private String emailCliente;

//    @ManyToOne
//    @JoinColumn(name = "usuario_id")
//    private Usuario usuario;
//    private Integer clienteId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public Emprestimo() {
    }

    public Emprestimo(Long id, Double valorEmprestimo, Integer quantidadeParcelas, LocalDate primeiraParcela) {
        this.id = id;
        this.valorEmprestimo = valorEmprestimo;
        this.quantidadeParcelas = quantidadeParcelas;
        this.primeiraParcela = primeiraParcela;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Emprestimo that = (Emprestimo) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        NumberFormat nf = NumberFormat.getNumberInstance(new Locale("pt", "BR"));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        StringBuilder builder = new StringBuilder();
        builder.append("Empréstimo número: ");
        builder.append(getId());
        builder.append(", Data da primeira parcela: ");
        builder.append(sdf.format(getPrimeiraParcela()));
        builder.append(", Cliente: ");
        builder.append(getCliente().getNome());
        builder.append("Valor total: ");
        builder.append(nf.format(getValorEmprestimo()));

        return builder.toString();
    }
}
