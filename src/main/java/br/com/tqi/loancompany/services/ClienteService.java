package br.com.tqi.loancompany.services;

import br.com.tqi.loancompany.domain.Cliente;
import br.com.tqi.loancompany.exceptions.ObjectNotFoundException;
import br.com.tqi.loancompany.repository.ClienteRepository;
import br.com.tqi.loancompany.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

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
        Cliente cliente = findById(id);
        try {
            clienteRepository.delete(cliente);
            usuarioRepository.delete(cliente.getUsuario());
        } catch (DataIntegrityViolationException exception) {
            throw new DataIntegrityViolationException("Não foi possível excluir esse usuário!");
        }
    }

    public Cliente replace(Long id, Cliente cliente) {
        Cliente clienteExistente = clienteRepository.getById(id);
        cliente.setEmprestimos(clienteExistente.getEmprestimos());
        cliente.setEnderecos(clienteExistente.getEnderecos());
        cliente.setUsuario(clienteExistente.getUsuario());
        cliente.setId(id);
        return clienteRepository.save(cliente);
    }

    public Cliente findByUsuario(Long userId) {
        return clienteRepository.findByUsuarioId(userId);
    }
}
