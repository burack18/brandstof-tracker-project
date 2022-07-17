package com.example.brandstoftracker.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Component
public class JWTTokenProvider {

    @Value("${jwt.secretkey}")
    private String secret;

    public String generateJwtToken(UserDetails user){

        String[] claims=getClaimsFromUser(user);

        //TODO DATE LOCALDATE CEVRILMELI
        return JWT.create()
                .withIssuer("Crew")
                .withIssuedAt(new Date())
                .withSubject(user.getUsername())
                .withArrayClaim("authorities",claims)
                .withExpiresAt(new Date(System.currentTimeMillis()+432_000_000))
                .sign(Algorithm.HMAC256("Secret".getBytes()));
    }

    private String[] getClaimsFromUser(UserDetails user) {
        Collection<SimpleGrantedAuthority> authorities=new ArrayList<>();
        String[] a=new String[authorities.size()];
        var d= user.getAuthorities().stream().map(x->x.toString()).collect(Collectors.toList());
        return d.toArray(new String[0]);
    }
    public List<GrantedAuthority> getAuthorities(String  token){
        String[] claims=getClaimsFromToken(token);
        return stream(claims).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    private String[] getClaimsFromToken(String token) {
        JWTVerifier verifier=getVerifier();
        return verifier.verify(token).getClaim("authorities").asArray(String.class);
    }

    private JWTVerifier getVerifier() {
        JWTVerifier verifier;
        try {
            Algorithm algorithm=Algorithm.HMAC256("Secret".getBytes());
            verifier=JWT.require(algorithm).withIssuer("Crew").build();
        }catch (JWTVerificationException e){
            throw new JWTVerificationException("Token not found");
        }
        return verifier;
    }
    public Authentication getAuthentication(UserDetails use, String username, List<GrantedAuthority> authorities, HttpServletRequest request){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new
                UsernamePasswordAuthenticationToken(use,username,authorities);
        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return usernamePasswordAuthenticationToken;
    }
    public boolean isTokenValid(String username,String token){
        JWTVerifier verifier=getVerifier();
        return StringUtils.isNotEmpty(username)&& !isTokenExpired(verifier,token);
    }

    private boolean isTokenExpired(JWTVerifier verifier, String token) {
        Date expiration=verifier.verify(token).getExpiresAt();
        return expiration.before(new Date());
    }
    public String getSubjectFromToken(String token){
        JWTVerifier verifier=getVerifier();
        DecodedJWT s=verifier.verify(token);

        return s.getSubject();
    }
}