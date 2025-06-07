package com.adi.myproject.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


// JWTServive is to generate the token

@Service
public class JwtService {

    private static final String secret = "abcdefghijklmnopqrstuvwxyz123456789abcdefghijkl";

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();

        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*1))
                .signWith(getKey(), SignatureAlgorithm.HS256).compact();
    }

    public Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Claims extractTokenBody(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUsername(String token) {
        return extractTokenBody(token).getSubject();
    }

    public Date extractExpiration(String token) {
        return extractTokenBody(token).getExpiration();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        Date expiration = extractExpiration(token);

        boolean checkExpiration = expiration.before(new Date());

        return (username.equals(userDetails.getUsername()) && !checkExpiration);
    }
}
