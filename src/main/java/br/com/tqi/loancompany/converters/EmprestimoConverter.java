package br.com.tqi.loancompany.converters;

import br.com.tqi.loancompany.domain.Emprestimo;
import br.com.tqi.loancompany.resources.dto.EmprestimoDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmprestimoConverter {

    public Emprestimo toEmprestimoModel(EmprestimoDto novoEmprestimo){

        return new Emprestimo(novoEmprestimo.getId(), novoEmprestimo.getValorEmprestimo(),
                 novoEmprestimo.getQuantidadeParcelas(), novoEmprestimo.getPrimeiraParcela());
    }

    public EmprestimoDto fromEmprestimoModel(Emprestimo novoEmprestimo){
         EmprestimoDto emprestimoDto = new EmprestimoDto(novoEmprestimo.getId(), novoEmprestimo.getValorEmprestimo(),
                novoEmprestimo.getQuantidadeParcelas(), novoEmprestimo.getPrimeiraParcela());
        emprestimoDto.setRendaCliente(novoEmprestimo.getCliente().getRenda());
        emprestimoDto.setEmailCliente(novoEmprestimo.getCliente().getEmail());
        return emprestimoDto;
    }

    public EmprestimoDto fromEmpretimoModelSimple(Emprestimo emprestimo){
        return new EmprestimoDto(emprestimo.getId(), emprestimo.getValorEmprestimo(), emprestimo.getQuantidadeParcelas());
    }

    public List<EmprestimoDto> fromEmprestimoListModelSimple(List<Emprestimo> emprestimos){
        return emprestimos.stream()
                .map(this::fromEmpretimoModelSimple)
                .collect(Collectors.toList());
    }

    public List<EmprestimoDto> fromEmprestimoListModel(List<Emprestimo> emprestimos){
        return emprestimos.stream()
                .map(this::fromEmprestimoModel)
                .collect(Collectors.toList());
    }
    public List<Emprestimo> toEmprestimoListModel(List<EmprestimoDto> emprestimos){
        return emprestimos.stream()
                .map(this::toEmprestimoModel)
                .collect(Collectors.toList());
    }
}
