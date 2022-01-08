package br.com.tqi.loancompany.converters;

import br.com.tqi.loancompany.domain.Cliente;
import br.com.tqi.loancompany.domain.Usuario;
import br.com.tqi.loancompany.repository.ClienteRepository;
import br.com.tqi.loancompany.resources.dto.ClienteDto;
import br.com.tqi.loancompany.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClienteConverter {

    @Autowired
    EnderecoConverter enderecoConverter;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ClienteService clienteService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    EmprestimoConverter emprestimoConverter;

    public Cliente toClienteModel(ClienteDto novoCliente) {
        Cliente cliente = new Cliente(novoCliente.getId(), novoCliente.getNome(), novoCliente.getEmail(), novoCliente.getSenha(),
                novoCliente.getCpf(), novoCliente.getRg(), novoCliente.getRenda());
        cliente.setEnderecos(enderecoConverter.toEnderecoListModel(novoCliente.getEnderecos()));
        cliente.getEnderecos().forEach(endereco -> endereco.setCliente(cliente));
        cliente.setEmprestimos(emprestimoConverter.toEmprestimoListModel(novoCliente.getEmprestimos()));
        cliente.getEmprestimos().forEach(emprestimo -> emprestimo.setCliente(cliente));
        cliente.setPerfil(novoCliente.getIdPerfil());
        cliente.setUsuario(toUsuario(novoCliente));
        return cliente;
    }

    public ClienteDto fromClienteModel(Cliente novoCliente) {
        ClienteDto clienteDto = new ClienteDto(novoCliente.getId(), novoCliente.getNome(), novoCliente.getEmail(),
                novoCliente.getSenha(), novoCliente.getCpf(), novoCliente.getRg(), novoCliente.getRenda(), novoCliente.getPerfil());
        clienteDto.setEnderecos(enderecoConverter.fromEnderecoListModel(novoCliente.getEnderecos()));
        clienteDto.setEmprestimos(emprestimoConverter.fromEmprestimoListModel(novoCliente.getEmprestimos()));
        clienteDto.setIdPerfil(novoCliente.getPerfil());
        return clienteDto;
    }

    public List<ClienteDto> fromClienteListModel(List<Cliente> cliente) {
        return cliente.stream()
                .map(this::fromClienteModel)
                .collect(Collectors.toList());
    }

    public List<Cliente> toClienteListModel(List<ClienteDto> clienteDtoList) {
        return clienteDtoList.stream()
                .map(this::toClienteModel)
                .collect(Collectors.toList());
    }

    public Usuario toUsuario(ClienteDto novoCliente){
        return new Usuario(novoCliente.getId(), novoCliente.getEmail(), passwordEncoder.encode(novoCliente.getSenha()), novoCliente.getIdPerfil());
    }
}
