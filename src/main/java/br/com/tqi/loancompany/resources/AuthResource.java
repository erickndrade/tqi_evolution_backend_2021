package br.com.tqi.loancompany.resources;

import br.com.tqi.loancompany.resources.dto.AutenticacaoDto;
import br.com.tqi.loancompany.resources.dto.DadosLogin;
import br.com.tqi.loancompany.security.AutenticacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/login")
public class AuthResource {

    @Autowired
    private AutenticacaoService autenticacaoService;

    @PostMapping
    public ResponseEntity<AutenticacaoDto> autenticar(@RequestBody DadosLogin dadosLogin) {
        return ResponseEntity.accepted().body(autenticacaoService.doLogin(dadosLogin));
    }

}
