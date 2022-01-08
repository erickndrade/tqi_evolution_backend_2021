package br.com.tqi.loancompany.resources.dto;

import br.com.tqi.loancompany.domain.Cliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteAutenticadoDto {

    private String token;
    private String tipo;

    public static ClienteAutenticadoDto toDto(Cliente cliente, String tipo){
        return new ClienteAutenticadoDto(cliente.getToken(), tipo);
    }

}
