package br.com.tqi.loancompany.repository;

import br.com.tqi.loancompany.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Transactional(readOnly=true)
    Optional<Cliente> findByEmail(String email);

    Cliente findByNome(String nome);

    Cliente findByUsuarioId(Long id);
}
