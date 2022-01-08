package br.com.tqi.loancompany.resources.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DadosLogin implements Serializable {

    private String email;
    private String senha;


    public ClienteDto toCliente(){
        return new ClienteDto(getEmail(), getSenha());
    }
}
