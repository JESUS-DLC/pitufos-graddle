package com.rdz.pitufos.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(UserDetails userDetails){
        return JWT.create()
                .withIssuedAt(new Date())
                .withSubject(userDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+900_000_000))
                .sign(Algorithm.HMAC256(secret.getBytes()));
    }

    private JWTVerifier getJWTVerfier(){
        JWTVerifier jwtVerifier;
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            jwtVerifier = JWT.require(algorithm).build();
        }catch (Exception e){
            throw new JWTVerificationException("Token cannot ve verified");
        }
        return jwtVerifier;
    }

    public String extractUsername(String jwt){
        JWTVerifier jwtVerifier = getJWTVerfier();
        return jwtVerifier.verify(jwt).getSubject();
    }

    public boolean validateToken(String jwt){
        JWTVerifier jwtVerifier = getJWTVerfier();
        Date expiration = jwtVerifier.verify(jwt).getExpiresAt();
        return !expiration.before(new Date());
    }
}
