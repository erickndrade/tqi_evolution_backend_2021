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

import javax.servlet.http.HttpServletRequest;
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

    @GetMapping("/me")
    public ResponseEntity<List<EmprestimoDto>> findAll(HttpServletRequest request){
        Long id = (Long) request.getAttribute("id");
        return ResponseEntity.ok(emprestimoConverter.fromEmprestimoListModelSimple(emprestimoService.findByUsuario(id)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmprestimoDto> detalharEmprestimo(@PathVariable Long id) {
        return ResponseEntity.ok(emprestimoConverter.fromEmprestimoModel(emprestimoService.findById(id)));
    }
    @GetMapping("/{id}/me")
    public ResponseEntity<EmprestimoDto> detalharEmprestimoUser(@PathVariable Long id, HttpServletRequest request) {
        return ResponseEntity.ok(emprestimoConverter.fromEmprestimoModel(emprestimoService.findById(id, (Long) request.getAttribute("id"))));
    }
    @GetMapping("/clientes/{id}")
    public ResponseEntity<EmprestimoDto> findByCliente(@PathVariable Long id){
        EmprestimoDto emprestimo = emprestimoConverter.fromEmprestimoModel(emprestimoService.findByCliente(id));
        return ResponseEntity.ok().body(emprestimo);
    }

    @PostMapping("/create/{clienteId}")
    public ResponseEntity<EmprestimoDto> inserirEmprestimoAdmin(@Valid @RequestBody EmprestimoDto novoEmprestimo,
                                                           @PathVariable Long clienteId, UriComponentsBuilder builder){
        Emprestimo emprestimo = emprestimoService.inserirEmprestimo(
                emprestimoConverter.toEmprestimoModel(novoEmprestimo), clienteId);
        URI uri = builder.path("/create/{clienteId}").buildAndExpand(emprestimo.getCliente().getId()).toUri();
        return ResponseEntity.created(uri).body(emprestimoConverter.fromEmprestimoModel(emprestimo));
    }

    @PostMapping("/create/me")
    public ResponseEntity<EmprestimoDto> inserirEmprestimo(@Valid @RequestBody EmprestimoDto novoEmprestimo,
                                                           HttpServletRequest request, UriComponentsBuilder builder){
        Emprestimo emprestimo = emprestimoService.inserirEmprestimoUser((Long) request.getAttribute("id"),
                                                        emprestimoConverter.toEmprestimoModel(novoEmprestimo));
        URI uri = builder.path("/create/{clienteId}").buildAndExpand(emprestimo.getCliente().getId()).toUri();
        return ResponseEntity.created(uri).body(emprestimoConverter.fromEmprestimoModel(emprestimo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEmprestimo(@PathVariable Long id){
        emprestimoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmprestimoDto> editarEmprestimo(@PathVariable Long id, @RequestBody EmprestimoDto emprestimoDto){
        return ResponseEntity.ok(emprestimoConverter.fromEmprestimoModel
                (emprestimoService.replace(id, emprestimoConverter.toEmprestimoModel(emprestimoDto))));
    }

}
