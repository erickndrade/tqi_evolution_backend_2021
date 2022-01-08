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
public class AutenticacaoDto {

    private String token;
    private String tipo;

    public static AutenticacaoDto toDto(Cliente cliente, String tipo){
        return new AutenticacaoDto(cliente.getToken(), tipo);
    }

}
