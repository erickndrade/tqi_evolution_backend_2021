package br.com.tqi.loancompany.resources;

import br.com.tqi.loancompany.converters.ClienteConverter;
import br.com.tqi.loancompany.domain.Cliente;
import br.com.tqi.loancompany.resources.dto.ClienteAutenticadoDto;
import br.com.tqi.loancompany.resources.dto.ClienteDto;
import br.com.tqi.loancompany.resources.dto.RegistroClienteDto;
import br.com.tqi.loancompany.services.AuthenticationService;
import br.com.tqi.loancompany.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteConverter clienteConverter;

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping
    public ResponseEntity<List<ClienteDto>> findAll() {
        return ResponseEntity.ok(clienteConverter.fromClienteListModel(clienteService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> detalharCliente(@PathVariable Integer id) {
        return ResponseEntity.ok(clienteConverter.fromClienteModel(clienteService.findById(id)));
    }

    @PostMapping("/signup")
    public ResponseEntity<ClienteAutenticadoDto> inserirCliente(@Valid @RequestBody ClienteDto novoCliente, RegistroClienteDto registro){
        Cliente cliente = clienteService.inserirCliente(clienteConverter.toClienteModel(novoCliente));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(cliente.getId()).toUri();
         ResponseEntity.created(uri).build();
        return new ResponseEntity<>(ClienteAutenticadoDto.toDto(cliente, "Bearer"), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Integer id){
        clienteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDto> editarCliente(@PathVariable Integer id, @RequestBody ClienteDto clienteDto){
        return ResponseEntity.ok(clienteConverter.fromClienteModel
                (clienteService.replace(id, clienteConverter.toClienteModel(clienteDto))));
    }
}
