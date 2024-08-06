package com.timmy.TimmyRoom.gloabl.auth;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class JwtUtil {

    private final Key key;
    private final long accessTokenExpTime;

    public JwtUtil(
            @Value("${jwt.secret}") String secretKey,
            @Value("${jwt.expiration_time}") long accessTokenExpTime
    ){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.accessTokenExpTime = accessTokenExpTime;
    }

    public String createAccessToken(Authentication authentication){
        Claims claims = Jwts.claims();
        claims.put("id", authentication.getName());

        long now = (new Date()).getTime();
        Date tokenValidity = new Date(now + accessTokenExpTime);

        return Jwts.builder()
                .setSubject(authentication.getName())
                .setClaims(claims)
                .setExpiration(tokenValidity)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // 토큰으로 클레임을 만들어 유저 객체를 생성 후 authentication 객체 리턴
    public Authentication getAuthentication(String token){
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        log.info("클레임 ID: {}", claims.get("id", String.class));
        User principal = new User(claims.get("id", String.class), "", List.of(new SimpleGrantedAuthority("ROLE_USER")));

        return new UsernamePasswordAuthenticationToken(principal, token, List.of(new SimpleGrantedAuthority("ROLE_USER")));
    }

    public boolean validateToken(String token){
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException e){
            log.info("Invalid JWT Token", e);
        } catch (ExpiredJwtException e){
            log.info("Expired JWT Token", e);
        } catch (UnsupportedJwtException e){
            log.info("Unsupported JWT Token", e);
        } catch (IllegalArgumentException e){
            log.info("JWT claims string is empty", e);
        }

        return false;
    }
}
