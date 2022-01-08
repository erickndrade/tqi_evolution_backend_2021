package br.com.tqi.loancompany.services;

import br.com.tqi.loancompany.domain.Cliente;
import br.com.tqi.loancompany.exceptions.ObjectNotFoundException;
import br.com.tqi.loancompany.repository.ClienteRepository;
import br.com.tqi.loancompany.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }


//    public Cliente registrate(Cliente cliente){
//        cliente.setToken(tokenService.generateToken(cliente));
//
//        return cliente;
//    }

    public Cliente inserirCliente(Cliente cliente) {
//        cliente.setSenha(passwordEncoder.encode(cliente.getSenha()));
        cliente.setToken(tokenService.generateToken(cliente));
        clienteRepository.save(cliente);
        enderecoRepository.saveAll(cliente.getEnderecos());
        return cliente;
    }

    public Cliente findById(Integer id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isPresent()) {
            return cliente.get();
        }
        throw new ObjectNotFoundException("Cliente não encontrado!");
    }

    public void deleteById(Integer id) {
        findById(id);
        try {
            clienteRepository.deleteById(id);
        } catch (DataIntegrityViolationException exception) {
            throw new DataIntegrityViolationException("Não foi possível excluir esse usuário!");
        }
    }

    public Cliente replace(Integer id, Cliente cliente) {
        cliente.setId(id);
        return clienteRepository.save(cliente);
    }

}
