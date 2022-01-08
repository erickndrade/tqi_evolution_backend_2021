package br.com.tqi.loancompany.security;


import br.com.tqi.loancompany.domain.Usuario;
import br.com.tqi.loancompany.exceptions.InvalidLoginException;
import br.com.tqi.loancompany.repository.UsuarioRepository;
import br.com.tqi.loancompany.resources.dto.AutenticacaoDto;
import br.com.tqi.loancompany.resources.dto.DadosLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

@Service
public class AutenticacaoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ImplUserDetailService implUserDetailService;

    public AutenticacaoDto doLogin(DadosLogin dadosLogin){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(dadosLogin.getEmail(), dadosLogin.getSenha());
        try {
            authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        }catch (Exception exception){
            throw new InvalidLoginException("Usuário e/ou senha inválido");
        }
        Optional<Usuario> usuario = usuarioRepository.findByEmail(dadosLogin.getEmail());
        UserDetails userDetails = implUserDetailService.loadUserByUsername(dadosLogin.getEmail());
        String token = tokenService.generateToken(userDetails, usuario.get());
        return new AutenticacaoDto(token, "Bearer");
    }








//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<Usuario> usuario = usuarioRepository.findByEmail(username);
//        if (usuario.isPresent()) {
//            return usuario.get();
//        }
//        throw new UsernameNotFoundException("Dados Inválidos!");
//    }
}
