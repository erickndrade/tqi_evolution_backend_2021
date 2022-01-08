package br.com.tqi.loancompany.services;

import br.com.tqi.loancompany.domain.Cliente;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${loancompany.jwt.expiration}")
    private Long expirationTime;

    @Value("${loancompany.jwt.secret}")
    private String signingKey;

    public String generateToken(Cliente cliente) {
        return Jwts.builder()
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setSubject(cliente.getNome())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS256, signingKey.getBytes())
                .compact();
    }

    public Claims decodeToken(String token) {
        return Jwts.parser()
                .setSigningKey(signingKey.getBytes())
                .parseClaimsJws(token)
                .getBody();
    }
}
