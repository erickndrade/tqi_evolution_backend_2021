package br.com.tqi.loancompany.security;


import br.com.tqi.loancompany.domain.Usuario;
import br.com.tqi.loancompany.exceptions.InvalidLoginException;
import br.com.tqi.loancompany.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

@Component
public class AuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public AuthTokenFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = recuperarToken(request);
        boolean valido = tokenService.isValid(token);
        if (valido){
           Long userId = authCliente(token);
           request.setAttribute("id", userId);
        }
        filterChain.doFilter(request, response);
    }

    private Long authCliente(String token) {
        Long userId = tokenService.getUserId(token);
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(userId);
        if(!optionalUsuario.isPresent()){
            throw new InvalidLoginException("Usuário não encontrado!");
        }
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(optionalUsuario.get().getEmail(),
                        optionalUsuario.get().getPassword(),
                        Collections.singletonList(new SimpleGrantedAuthority(optionalUsuario.get().getPerfil().getDescricao())));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    return userId;
    }

    private String recuperarToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer")){
            return null;
        }
        return token.substring(7, token.length());
    }
}
