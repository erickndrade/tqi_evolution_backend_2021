package br.com.tqi.loancompany.repository;

import br.com.tqi.loancompany.domain.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    Emprestimo findClienteById(Long id);

    List<Emprestimo> findByClienteId(Long id);
}
