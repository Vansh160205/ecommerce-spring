package com.project.shopease.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String secret;
     private Key key;
     private static final long EXPIRATION_TIME = 1000*60*60*24;

     @PostConstruct
    public void init(){
        byte[] decodedKey = Base64.getDecoder().decode(secret);
         this.key = Keys.hmacShaKeyFor(decodedKey);
     }

     public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
     }
     public Date extractExpiration(String token){
         return extractClaim(token, Claims::getExpiration);
     }
     public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
         final Claims claims = extractAllClaims(token);
         return claimsResolver.apply(claims);
     }

     public String generateToken(UserDetails userDetails){
         return generateToken(new HashMap<>(),userDetails);
     }

     public String generateToken(Map<String , Object> extractClaims, UserDetails userDetails){
            return Jwts.builder()
                    .setClaims(extractClaims)
                    .setSubject(userDetails.getUsername())
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
                    .signWith(key, SignatureAlgorithm.HS512)
                    .compact();
     }

     public boolean isTokenValid(String token, UserDetails userDetails){
            final String username = userDetails.getUsername();
            return (username.equals(extractUsername(token)) && !isTokenExpired(token));
     }
     public boolean isTokenExpired(String token){
         return extractExpiration(token).before(new Date());
     }
     public Claims extractAllClaims(String token){
         return Jwts.parserBuilder()
                 .setSigningKey(key)
                 .build()
                 .parseClaimsJws(token)
                 .getBody();
     }
}
