package com.example.cardataproject.security.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.security.SignatureException;
import java.util.Date;

@Service
public class JwtTokenProvider {

    private String jwtSecret = "984hg493gh0439rthr0429uruj2309yh937gc763fe87t3f89723gf";

    private long jwtLifeTime = 600000;

    public String createToken(String userName) {

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtLifeTime);

        Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes());

        return Jwts.builder()
                .setSubject(userName)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

    }

    public boolean validateToken(String token) throws InvalidJwtException {

        try {
            Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes());

            Jwts
                    .parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
        } catch (JwtException e) {
            //Invalid JWT signature
            throw new InvalidJwtException("Invalid JWT token" + e.getMessage());
        }
        return true;
    }

    public String getUsernameFromJwt(String token) {

        Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes());

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        // вытаскиваем claims (из части payload нашего JWT)
        // из них берем содержимое поля subject
        return claims.getSubject();

    }
}
