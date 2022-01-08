package br.com.tqi.loancompany.repository;

import br.com.tqi.loancompany.domain.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Integer> {

    Emprestimo findClienteById(Integer id);
}
