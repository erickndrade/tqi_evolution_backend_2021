package br.com.tqi.loancompany.resources;

import br.com.tqi.loancompany.converters.EmprestimoConverter;
import br.com.tqi.loancompany.domain.Cliente;
import br.com.tqi.loancompany.domain.Emprestimo;
import br.com.tqi.loancompany.resources.dto.EmprestimoDto;
import br.com.tqi.loancompany.services.ClienteService;
import br.com.tqi.loancompany.services.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/emprestimos")
public class EmprestimoResource {

    @Autowired
    private EmprestimoService emprestimoService;

    @Autowired
    private EmprestimoConverter emprestimoConverter;

    @GetMapping
    public ResponseEntity<List<EmprestimoDto>> findAll(){
        return ResponseEntity.ok(emprestimoConverter.fromEmprestimoListModel(emprestimoService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmprestimoDto> detalharEmprestimo(@PathVariable Integer id) {
        return ResponseEntity.ok(emprestimoConverter.fromEmprestimoModel(emprestimoService.findById(id)));
    }
    @GetMapping("/clientes/{id}")
    public ResponseEntity<EmprestimoDto> findByCliente(@PathVariable Integer id){
        EmprestimoDto emprestimo = emprestimoConverter.fromEmprestimoModel(emprestimoService.findByCliente(id));
        return ResponseEntity.ok().body(emprestimo);
    }

    @PostMapping("/create/{clienteId}")
    public ResponseEntity<EmprestimoDto> inserirEmprestimo(@Valid @RequestBody EmprestimoDto novoEmprestimo,
                                                           @PathVariable Integer clienteId, UriComponentsBuilder builder){
        Emprestimo emprestimo = emprestimoService.inserirEmprestimo(
                emprestimoConverter.toEmprestimoModel(novoEmprestimo), clienteId);
        URI uri = builder.path("/create/{clienteId}").buildAndExpand(emprestimo.getCliente().getId()).toUri();
        return ResponseEntity.created(uri).body(emprestimoConverter.fromEmprestimoModel(emprestimo));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEmprestimo(@PathVariable Integer id){
        emprestimoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmprestimoDto> editarEmprestimo(@PathVariable Integer id, @RequestBody EmprestimoDto emprestimoDto){
        return ResponseEntity.ok(emprestimoConverter.fromEmprestimoModel
                (emprestimoService.replace(id, emprestimoConverter.toEmprestimoModel(emprestimoDto))));
    }

}
