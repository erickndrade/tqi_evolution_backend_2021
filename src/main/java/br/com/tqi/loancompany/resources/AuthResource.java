package br.com.tqi.loancompany.resources;

import br.com.tqi.loancompany.domain.Cliente;
import br.com.tqi.loancompany.resources.dto.ClienteAutenticadoDto;
import br.com.tqi.loancompany.resources.dto.DadosLogin;
import br.com.tqi.loancompany.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<ClienteAutenticadoDto> autenticar(@RequestBody DadosLogin dadosLogin, @RequestHeader String Authorization) {
        Cliente cliente = authenticationService.authenticate(dadosLogin, Authorization);
        return new ResponseEntity<>(ClienteAutenticadoDto.toDto(cliente, "Bearer"), HttpStatus.ACCEPTED);
    }
}
