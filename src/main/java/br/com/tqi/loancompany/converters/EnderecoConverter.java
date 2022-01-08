package br.com.tqi.loancompany.converters;

import br.com.tqi.loancompany.domain.Endereco;
import br.com.tqi.loancompany.resources.dto.EnderecoDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EnderecoConverter {

    public static Endereco toEnderecoModel(EnderecoDto endereco){
        return new Endereco(endereco.getId(), endereco.getLogradouro(), endereco.getNumero(), endereco.getComplemento(),
                endereco.getBairro(), endereco.getCep(), endereco.getCidade(), endereco.getEstado(), null);

    }

    public static EnderecoDto fromEnderecoModel(Endereco endereco){
        return new EnderecoDto(endereco.getId(), endereco.getLogradouro(), endereco.getNumero(), endereco.getComplemento(),
                endereco.getBairro(), endereco.getCep(), endereco.getCidade(), endereco.getEstado());
    }

    public List<EnderecoDto> fromEnderecoListModel(List<Endereco> enderecos){

        return enderecos.stream()
                .map(EnderecoConverter::fromEnderecoModel)
                .collect(Collectors.toList());
    }

    public List<Endereco> toEnderecoListModel(List<EnderecoDto> enderecoDtoList){

        return enderecoDtoList.stream()
                .map(EnderecoConverter::toEnderecoModel)
                .collect(Collectors.toList());
    }
}
