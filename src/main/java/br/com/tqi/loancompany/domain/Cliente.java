package br.com.tqi.loancompany.domain;

import br.com.tqi.loancompany.domain.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "CLIENTE")
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @Column(unique = true)
    private String email;

    private String senha;

    private String cpf;

    private String rg;

    private String token;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Endereco> enderecos = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private List<Emprestimo> emprestimos = new ArrayList<>();

    private BigDecimal renda;

    @Column(name = "PERFIS")
    @Enumerated(EnumType.STRING)
    private Perfil perfil;

    public Cliente(){
        setPerfil(Perfil.CLIENTE);
    }

    public Cliente(Integer id, String nome, String email, String senha, String cpf, String rg, BigDecimal renda) {
        super();
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.rg = rg;
        this.renda = renda;
        setPerfil(Perfil.CLIENTE);
    }

//    public <E> Cliente(String email, String senha, ArrayList<E> es) {
//
//    }
//

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cliente other = (Cliente) obj;
        if (id == null) {
            return other.id == null;
        } else return id.equals(other.id);
    }
}
