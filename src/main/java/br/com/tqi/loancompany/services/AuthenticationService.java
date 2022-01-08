package br.com.tqi.loancompany.services;

import br.com.tqi.loancompany.domain.Cliente;
import br.com.tqi.loancompany.exceptions.AuthorizationException;
import br.com.tqi.loancompany.repository.ClienteRepository;
import br.com.tqi.loancompany.resources.dto.DadosLogin;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthenticationService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private TokenService tokenService;

    public Cliente authenticate(DadosLogin dados, String token){
        Cliente cliente = clienteRepository.findByEmail(dados.getEmail()).orElseThrow(AuthorizationException::new);
        if (dados.getSenha().equals(cliente.getSenha()) && !token.isEmpty() && validate(token)){
            return cliente;
        }
        else {
            throw new AuthorizationException("Email e/ou senha Inválido");
        }
    }
    private boolean validate(String token) {
        try {
            String tokenTratado = token.replace("Bearer ", "");
            Claims claims = tokenService.decodeToken(tokenTratado);
            System.out.println(claims.getIssuer());
            System.out.println(claims.getIssuedAt());
            //Verifica se o token está expirado
            if (claims.getExpiration().before(new Date(System.currentTimeMillis()))) throw new AuthorizationException();
            System.out.println(claims.getExpiration());
            return true;
        } catch (AuthorizationException et){
            et.printStackTrace();
            throw et;
        } catch (Exception e) {
            e.printStackTrace();
            throw new AuthorizationException("Token Inválido!!!");
        }
    }


}
