package br.com.tqi.loancompany.security;

import br.com.tqi.loancompany.domain.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TokenService {

//    private static final long expirationTime = 86400000;

    public static final String AUTHORITIES_CLAIM = "authorities";

    public static final String SYSTEM_USER_CLAIM = "usuario";

    @Value("${loancompany.jwt.expiration}")
    private Long expirationTime;

    @Value("${loancompany.jwt.secret}")
    private String secret;

    public String generateToken(UserDetails userDetails, Usuario systemUser) {
        return Jwts.builder()
                .setClaims(getClaims(userDetails, systemUser))
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
    private Map<String, Object> getClaims(UserDetails userDetails, Usuario user) {
        Map<String, Object> claimsMap = new HashMap<>();

        claimsMap.put(AUTHORITIES_CLAIM, getAuthoritiesString(userDetails));
        claimsMap.put(SYSTEM_USER_CLAIM, user.getId());

        return claimsMap;
    }

    private String getAuthoritiesString(UserDetails userDetails) {
        return userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
    }

//    public String gerarToken(Authentication authentication) {

//        Usuario logado = (Usuario) authentication.getPrincipal();
//
//        return Jwts.builder()
//                .setIssuer("API do Fórum da Alura")
//                .setSubject(logado.getId().toString())
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
//                .signWith(SignatureAlgorithm.HS256, secret)
//                .compact();
//
//
//    }

    public boolean isValid(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public Long getUserId(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(this.secret)
                .parseClaimsJws(token)
                .getBody();
        return (claims.get("usuario", Long.class));
    }
}
