package br.com.tqi.loancompany.services;

import br.com.tqi.loancompany.domain.Cliente;
import br.com.tqi.loancompany.exceptions.ObjectNotFoundException;
import br.com.tqi.loancompany.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Cliente inserirCliente(Cliente cliente) {
        clienteRepository.save(cliente);
        return cliente;
    }

    public Cliente findById(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isPresent()) {
            return cliente.get();
        }
        throw new ObjectNotFoundException("Cliente não encontrado!");
    }

    public void deleteById(Long id) {
        findById(id);
        try {
            clienteRepository.deleteById(id);
        } catch (DataIntegrityViolationException exception) {
            throw new DataIntegrityViolationException("Não foi possível excluir esse usuário!");
        }
    }

    public Cliente replace(Long id, Cliente cliente) {
        cliente.setId(id);
        return clienteRepository.save(cliente);
    }

    public Cliente findByUsuario(Long userId) {
        return clienteRepository.findByUsuarioId(userId);
    }
}
