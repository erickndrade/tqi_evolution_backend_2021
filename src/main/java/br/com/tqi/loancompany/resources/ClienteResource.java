package br.com.tqi.loancompany.resources;

import br.com.tqi.loancompany.converters.ClienteConverter;
import br.com.tqi.loancompany.domain.Cliente;
import br.com.tqi.loancompany.resources.dto.AutenticacaoDto;
import br.com.tqi.loancompany.resources.dto.ClienteDto;
import br.com.tqi.loancompany.resources.dto.DadosLogin;
import br.com.tqi.loancompany.security.AutenticacaoService;
import br.com.tqi.loancompany.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private AutenticacaoService autenticacaoService;

    @Autowired
    private ClienteConverter clienteConverter;

    @GetMapping
    public ResponseEntity<List<ClienteDto>> findAll() {
        return ResponseEntity.ok(clienteConverter.fromClienteListModel(clienteService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> detalharClienteAdmin(@PathVariable Long id) {
        return ResponseEntity.ok(clienteConverter.fromClienteModel(clienteService.findById(id)));
    }
    @GetMapping("/me")
    public ResponseEntity<ClienteDto> detalharClienteUser(@RequestAttribute("id") Long userId) {
        return ResponseEntity.ok(clienteConverter.fromClienteModel(clienteService.findByUsuario(userId)));
    }

    @PostMapping("/signup")
    public ResponseEntity<AutenticacaoDto> inserirCliente(@Valid @RequestBody ClienteDto novoCliente){
        Cliente cliente = clienteService.inserirCliente(clienteConverter.toClienteModel(novoCliente));
        AutenticacaoDto response = autenticacaoService.doLogin(new DadosLogin(novoCliente.getEmail(), novoCliente.getSenha()));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id){
        clienteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDto> editarCliente(@PathVariable Long id, @RequestBody ClienteDto clienteDto){
        return ResponseEntity.ok(clienteConverter.fromClienteModel
                (clienteService.replace(id, clienteConverter.toClienteModel(clienteDto))));
    }
}
