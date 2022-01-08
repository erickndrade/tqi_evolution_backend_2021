package br.com.tqi.loancompany.resources.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class UsuarioDto {

    private String email;
    private String senha;

    public ClienteDto toCliente(){
        return new ClienteDto(getEmail(), getSenha());
    }

}
